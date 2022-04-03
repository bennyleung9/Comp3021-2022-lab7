package com.example.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class NoteBook implements Serializable{
    private ArrayList<Folder> folders;
    private static final long serialVersionUID = 1L;
    public NoteBook() {
        this.folders = new ArrayList<Folder>();
    }
    public NoteBook(ArrayList<Folder> folders) {
        this.folders = folders;
    }
    public NoteBook(String file) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fis);
        NoteBook n = (NoteBook) in.readObject();
        in.close();
        this.folders = n.getFolders();
    }        
    public boolean createTextNote(String folderName, String title) {
        TextNote note = new TextNote(title);
        return insertNote(folderName, note);
    }
    public boolean createTextNote(String folderName, String title, String content) {
        TextNote note = new TextNote(title, content);
        return insertNote(folderName, note);
    }
    public boolean createImageNote(String folderName, String title) {
        ImageNote note = new ImageNote(title);
        return insertNote(folderName, note);
    }
    public ArrayList<Folder> getFolders() {
        return this.folders;
    }
    public boolean insertNote(String folderName, Note note) {
        Folder f = null;
        for (Folder f1 : folders) {
            if(f1.getName() == folderName){
                f = f1;
            }
        }
        if (f == null) {
            f = new Folder(folderName);
            this.folders.add(f);
        }
        boolean exist = false;
        for (Note n : f.getNotes()) {
            if(n.equals(note)){
                System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
                exist = true;
                break;
            }
        }
        if(!exist){
            f.addNote(note);
        }
        return !exist;
    }
    public void sortFolders() 
	{ 
		for (Folder f1 : folders){
            f1.sortNotes();
        }
        Collections.sort(this.folders);
    }
    public List<Note> searchNotes(String keywords){
        ArrayList<Note> searchNotes = new ArrayList<Note>();
        System.out.println(keywords);
        for(Folder f:folders){
                searchNotes.addAll(f.searchNotes(keywords));
            }
        return searchNotes;
    }
    public boolean save(String file){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            System.out.print("Failed Save");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}