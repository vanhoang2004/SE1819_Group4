//package com.example.demo.controller;
//
//import com.example.demo.data.*;
//import com.example.demo.entity.*;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.ui.Model;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ListMaterialTest {
//    @InjectMocks
//    private ExampleController controller;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private SubjectRepository subjectRepository;
//
//    @Mock
//    private ChapterRepository chapterRepository;
//
//    @Mock
//    private MaterialRepository materialRepository;
//
//    @Mock
//    private MockTestRepository mockTestRepository;
//
//    @Mock
//    private Model model;
//
//    @Mock
//    private Authentication auth;
//
//    @Mock
//    private SecurityContext securityContext;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        SecurityContextHolder.setContext(securityContext);
//        when(securityContext.getAuthentication()).thenReturn(auth);
//        when(auth.getName()).thenReturn("user1");
//        User user = new User();
//
//        user.setId(1);
//        user.setName("user1");
//        when(userRepository.findUserByName("user1")).thenReturn(user);
//    }
////    @Test
////    public void listMaterial() {
////        int userId=1;
////        Subject subject = new Subject();
////        subject.setId(1);
////        when(subjectRepository.findSubjectByUserID(userId)).thenReturn(subject);
////
////        Chapters chapters = new Chapters();
////        List<Chapters> chaptersList = new ArrayList<>();
////        for (int i = 0; i < 5; i++) {
////            Chapters chapter = new Chapters();
////            chapter.setId(i);
////            chaptersList.add(chapter);
////        }
////        when(chapterRepository.findChapterBySubject(subject.getId())).thenReturn(chaptersList);
////        Materials mockMaterial = new Materials(subject.getId(),chaptersList.get(1).getId(),"Title 1","Title 2");
////        mockMaterial.setId(1);
////        when(materialRepository.findMaterialbyID(1)).thenReturn(mockMaterial);
////
////
////
////
////        String actual = controller.listMaterial(model, 1);
////
////        // Assert
////        assertEquals("edit-material", actual);
////        assertEquals(subject, model.getAttribute("subject"));
////        assertEquals(chaptersList, model.getAttribute("chapter"));
////        assertEquals(mockMaterial, model.getAttribute("material"));
////
////        assertTrue(mockMaterial.containsAll(mockTestRepository.findMockTestByUserId(1)));
////
////
////    }
//    private Materials createMaterials(Integer id,Chap){
//
//    }
//
//    public void listMaterialSuccess() {
//        Subject subject = new Subject();
//        subject.setId(1);
//        when(subjectRepository.findSubjectByUserID(userId)).thenReturn(subject);
//
//        Chapters chapters = new Chapters();
//        List<Chapters> chaptersList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Chapters chapter = new Chapters();
//            chapter.setId(i);
//            chaptersList.add(chapter);
//        }
//        when(chapterRepository.findChapterBySubject(subject.getId())).thenReturn(chaptersList);
////        List<Materials> mockMaterial = new Materials(subject.getId(),chaptersList.get(1).getId(),"Title 1","Title 2");
//        mockMaterial.setId(1);
//        Materials test1 = createMockTest(1, "Test1");
//        MockTest test2 = createMockTest(2, "Test2");
//        List<MockTest> mock = new ArrayList<>();
//        mock.add(test1);
//        mock.add(test2);
//        when(subjectRepository.findSubjectByUserID(1)).thenReturn(subject);
//        when(mockTestRepository.searchMockTest(1,"Test1")).thenReturn(mock);
//        String actual = controller.listMockTest(model, "Test1");
//        assertTrue(mock.containsAll(mockTestRepository.findMockTestByUserId(1)));
//
//        assertEquals("home", actual);
//        verify(model).addAttribute("subject", subject);
//        verify(model).addAttribute("mock", mock);
//    }
//}