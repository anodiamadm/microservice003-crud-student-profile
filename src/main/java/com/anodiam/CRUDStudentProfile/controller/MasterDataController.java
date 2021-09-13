package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.db.repository.masterData.BoardRepository;
import com.anodiam.CRUDStudentProfile.db.repository.masterData.LevelRepository;
import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import com.anodiam.CRUDStudentProfile.model.masterData.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/master-data")
@CrossOrigin
public class MasterDataController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private LevelRepository levelRepository;

//  REVIEW COMMENT::  TRY - CATCH needs to narrow down to specific exception instead of just EXCEPTION
//  @GetMapping("boards") :: List all boards - to populate dropdowns in the frontend
    @GetMapping("boards")
    @ResponseBody
    public List<Board> getAllBoards() throws Exception {
        try {
            List<Board> boardList = boardRepository.findAll();
            return boardList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//  REVIEW COMMENT::  TRY - CATCH needs to narrow down to specific exception instead of just EXCEPTION
//  @GetMapping("levels") :: List all levels - to populate dropdowns in the frontend
    @GetMapping("levels")
    @ResponseBody
    public List<Level> getAllLevels() throws Exception {
        try {
            List<Level> levelList = levelRepository.findAll();
            return levelList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
