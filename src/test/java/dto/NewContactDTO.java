package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewContactDTO {
    String address;
    String description;
    String email;
    String id; //always 0, need for API
    String lastName;
    String name;
    String phone;
}


