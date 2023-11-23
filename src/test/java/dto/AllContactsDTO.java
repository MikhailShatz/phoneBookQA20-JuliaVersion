package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllContactsDTO {
    NewContactDTO[] contacts;
}
