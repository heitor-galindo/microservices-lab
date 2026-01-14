package com.skistation.reservationms.clients;

import com.skistation.reservationms.configuration.FeignClientConfigStudent;
import com.skistation.reservationms.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "STUDENTMS", configuration = FeignClientConfigStudent.class)
public interface StudentClient {

  @GetMapping("/students/{id}")
  public StudentDTO getStudentById(@PathVariable Long id);
}
