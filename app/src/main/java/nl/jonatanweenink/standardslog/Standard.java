package nl.jonatanweenink.standardslog;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Laptop Weenink on 5-3-2018.
 */

public class Standard implements Serializable {
    private int id;
    private String title;
    private String artist;
    private String dateAdded;
    private String status;
    private String notes;
    private String key;
    public Standard() {
    }
    public Standard(String title, String artist, String status, String notes, String key) {
        this(-1, title, artist, status, notes, key);
        this.dateAdded = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }
    public Standard(int id, String title, String artist, String status, String notes, String key) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.status = status;
        this.notes = notes;
        this.key = key;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}


