package com.example.atividades.atividade15;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.TextDetection;

import java.io.*;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class DetectTextImageTest {

    private RekognitionClient mockRekognitionClient;
    private DetectTextImage detectTextImage;

    @Before
    public void setUp() {
        mockRekognitionClient = Mockito.mock(RekognitionClient.class);
        detectTextImage = new DetectTextImage("test-image.jpg", mockRekognitionClient);
    }

    @Test
    public void testDetectTextLabels() throws IOException {
        TextDetection detection = TextDetection.builder()
                .detectedText("Hello World")
                .confidence(99.0f)
                .id(1)
                .parentId(0)
                .type("LINE")
                .build();
        DetectTextResponse response = DetectTextResponse.builder()
                .textDetections(Arrays.asList(detection))
                .build();

        when(mockRekognitionClient.detectText(any(DetectTextRequest.class))).thenReturn(response);

        File resultFile = new File("detected_text_results.txt");
        if (resultFile.exists()) {
            resultFile.delete();
        }

        detectTextImage.detectTextLabels("detected_text_results.txt");

        verify(mockRekognitionClient, times(1)).detectText(any(DetectTextRequest.class));

        BufferedReader reader = new BufferedReader(new FileReader("detected_text_results.txt"));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();

        String expectedContent = "Detected: Hello World\n" +
                "Confidence: 99.0\n" +
                "Id: 1\n" +
                "Parent Id: 0\n" +
                "Type: LINE\n\n";
        assertEquals(expectedContent, content.toString());
    }

    @Test(expected = RekognitionException.class)
    public void testDetectTextLabelsWithException() {
        when(mockRekognitionClient.detectText(any(DetectTextRequest.class))).thenThrow(RekognitionException.builder().message("Service error").build());

        detectTextImage.detectTextLabels("detected_text_results.txt");
    }
}
