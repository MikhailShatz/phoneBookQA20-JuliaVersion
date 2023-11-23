package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContactDTO {
   String id;
   String name;
   String lastName;
   String email;
   String phone;
   String address;
   String description;
}
