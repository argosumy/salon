package spdu2022.java.project.beutysalon.staffregistration.controllers;

import com.amazonaws.util.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import spdu2022.java.project.beutysalon.staffregistration.services.StaffFileStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StaffFileStorageController.class)
class StaffFileStorageControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StaffFileStorage staffFileStorage;

    @Test
    void saveAvatar() throws Exception {
        mockMvc.perform(multipart("/api/v1/staff/{staffId}/file", 1)
                .file(new MockMultipartFile("file", getMultipartFileBytes())))
                .andExpect(status().is(201));
    }

    @Test
    @DisplayName("Response has status 'Bad Request' if staffId < 1")
    void saveAvatarStaffIdNotValid() throws Exception {
        mockMvc.perform(multipart("/api/v1/staff/{staffId}/file", 0)
                .file(new MockMultipartFile("file", getMultipartFileBytes())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAvatar() throws Exception {
        mockMvc.perform(delete("/api/v1/staff/{staffId}/file", 1))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("Response has status 'Bad Request' if staffId < 1")
    void deleteAvatarStaffIdNotValid() throws Exception {
        mockMvc.perform(delete("/api/v1/staff/{staffId}/file", 0))
                .andExpect(status().isBadRequest());
    }

    private byte[] getMultipartFileBytes() throws IOException {
        File file = new File("src/test/resources/file/avatar");
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file",
                file.getName(), "image/jpeg", IOUtils.toByteArray(input)).getBytes();
    }
}