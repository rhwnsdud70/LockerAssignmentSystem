package yu22112077.LockerAssignmentSystem.controller;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yu22112077.LockerAssignmentSystem.model.entity.User;
import yu22112077.LockerAssignmentSystem.repository.UserRepository;

import java.io.*;
        import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ExcelToAccountController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload-excel")
    public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {
        List<String> skippedIds = new ArrayList<>();
        int createdCount = 0;

        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String name = row.getCell(1).getStringCellValue().trim();
                String major = row.getCell(2).getStringCellValue().trim();
                String studentNumber = String.valueOf((long) row.getCell(3).getNumericCellValue());
                String feeStatus = row.getCell(6).getStringCellValue().trim();
                String role = feeStatus.equalsIgnoreCase("O") ? "납부자" : "미납부자";

                if (userRepository.existsById(studentNumber)) {
                    skippedIds.add(studentNumber);
                    continue;
                }

                User user = new User();
                user.setId(studentNumber);
                user.setPassword(studentNumber);
                user.setName(name);
                user.setMajor(major);
                user.setStudentNumber(studentNumber);
                user.setRole(role);
                user.setAdministrator(false);

                userRepository.save(user);
                createdCount++;
            }

            StringBuilder report = new StringBuilder();
            report.append("[사용자 업로드 결과]\n");
            report.append("등록 완료: ").append(createdCount).append("명\n");
            if (!skippedIds.isEmpty()) {
                report.append("중복 제외: ").append(skippedIds.size()).append("명\n");
                report.append("중복된 ID 목록: ").append(String.join(", ", skippedIds)).append("\n");
            }

            ByteArrayInputStream bis = new ByteArrayInputStream(report.toString().getBytes());
            InputStreamResource resource = new InputStreamResource(bis);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=upload_result.txt")
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("오류 발생: " + e.getMessage());
        }
    }
}
