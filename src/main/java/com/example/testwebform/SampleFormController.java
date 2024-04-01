package com.example.testwebform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class SampleFormController {

    @GetMapping("/hello-world")
    public String hello() {
        return "hello1234";
    }

    @PostMapping("/diving-form")
    public String processFormData(@RequestBody String incomingRequest) throws Exception {
        System.out.println(incomingRequest);
        writeOutToFile(incomingRequest);
        return "Received string" + incomingRequest;
    }

    private void writeOutToFile(String jsonString) throws Exception {
        // Get the path to the resources folder
        String resourcesPath = SampleFormController.class.getClassLoader().getResource("").getPath();

        // Define the file name
        String fileName = "output.json";

        // Construct the file path
        String filePath = resourcesPath + fileName;

        try {
            // Create a FileWriter object
            FileWriter fileWriter = new FileWriter(filePath);

            // Wrap the FileWriter object in a BufferedWriter for efficient writing
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the JSON string to the file
            bufferedWriter.write(jsonString);

            // Close the BufferedWriter
            bufferedWriter.close();

            System.out.println("JSON string successfully written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
