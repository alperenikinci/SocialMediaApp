package com.alperenikinci.dto.response;
import com.alperenikinci.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivateResponseDto {
    private  Long id;
    private Status status;
}