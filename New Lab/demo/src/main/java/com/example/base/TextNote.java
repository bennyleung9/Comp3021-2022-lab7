package com.example.base;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextNote extends Note{
    private String content;
    public TextNote(String title){
        super(title);
        this.content = null;
    }
    public TextNote(String title, String content){
        super(title);
        this.content = content;
    }
    public TextNote(File f) throws IOException {
        super(f.getName());
        this.content = getTextFromFile(f.getAbsolutePath());
    }
        
    public String getContent(){
        return this.content;
    }
    private String getTextFromFile(String absolutePath) throws IOException {
        String result = "";
        BufferedReader br = new BufferedReader(new FileReader(absolutePath));
        try {
            String line = br.readLine();
            while (line != null) {
                result = result + line;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            br.close();
        }
        return result;
    }
    public void exportTextToFile(String pathFolder) throws IOException{
        try {
            System.out.println(this.getTitle());
            String filename = this.getTitle() + ".txt";
            filename = filename.replaceAll(" ","_");
            File file = new File(filename);
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                writer.write(this.getContent());
                writer.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                System.out.println("File already exists.");
            } 
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
}    
