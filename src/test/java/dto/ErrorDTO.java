package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    String timestamp;
    int status;
    String error;
    String message;
    String path;

}
