package com.example.lab_01_v2.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditAlbumForm {
    private String title;
    private String author;
    private String newTitle;
    private String newAuthor;
}
