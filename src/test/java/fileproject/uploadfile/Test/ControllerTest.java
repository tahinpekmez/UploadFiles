package fileproject.uploadfile.Test;

import fileproject.uploadfile.Models.File;
import fileproject.uploadfile.Repositories.FileRepository;
import fileproject.uploadfile.Requests.LoginRequest;
import fileproject.uploadfile.Requests.SignupRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.stream.Stream;

import static io.jsonwebtoken.lang.Classes.getResourceAsStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@AutoConfigureMockMvc
public class ControllerTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testLogin() throws Exception {
        String uri = "/auth/login";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("murat");
        loginRequest.setPassword("admin");
        String inputJson = super.mapToJson(loginRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.startsWith("ey"));
    }

    @Test
    public void testSignup() throws Exception {
        String uri = "/auth/signup";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("ipek");
        signupRequest.setPassword("admin");
        String inputJson = super.mapToJson(signupRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.printf(content);
        assertEquals("{\"message\":\"User registered successfully!\"}", content);
    }

    @Test
    public void testUpload() throws Exception {
        testLogin();
        MockMultipartFile file = new MockMultipartFile("file", "ISTQB.jpeg", "image/jpeg", getResourceAsStream("ISTQB.jpeg"));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/")
                        .file(file)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testInvalidUpload() throws Exception {
        testLogin();
        String text = "Text to be uploaded.";
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", text.getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/")
                        .file(file)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed());
    }

    @Test
    public void getFileList() throws Exception {
        testLogin();
        String uri = "/files";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        String productlist = super.mapToJson(content);
        assertTrue(productlist.length() > 0);
    }

}