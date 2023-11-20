package manager;

import dto.NewContactDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper{
    public ContactHelper(WebDriver driver) {
        super(driver);
    }
    String phoneLocal = "";

    By btnAddNewContact = By.xpath("//a[@href='/add']");
    By inputNameAddContact = By.xpath("//input[@placeholder='Name']");
    By inputLastNameAddContact = By.xpath("//input[@placeholder='Last Name']");
    By inputPhoneAddContact = By.xpath("//input[@placeholder='Phone']");
    By inputEmailAddContact = By.xpath("//input[@placeholder='email']");
    By inputAddressAddContact = By.xpath("//input[@placeholder='Address']");
    By inputDescriptionAddContact = By.xpath("//input[@placeholder='description']");
    By btnSaveNewContact = By.xpath("//button/b");
    By btnRemoveContact = By.xpath("//button[text()='Remove']");

    By textH3ContactList = By.xpath("//h3");

   // By phoneNumberInContacts= By.xpath(String.format("//h3[text()='%s']", phoneLocal));
   public By getPhoneNumberInContactsLocator(String phone) {
       return By.xpath(String.format("//h3[contains(text(),'%s')]", phone));
   }

    public void addNewContact(NewContactDTO newContactDTO) {
        clickBase(btnAddNewContact);
        typeTextBase(inputNameAddContact, newContactDTO.getName());
        typeTextBase(inputLastNameAddContact, newContactDTO.getLastName());
        typeTextBase(inputPhoneAddContact, newContactDTO.getPhone());
        typeTextBase(inputEmailAddContact, newContactDTO.getEmail());
        typeTextBase(inputAddressAddContact, newContactDTO.getAddress());
        typeTextBase(inputDescriptionAddContact, newContactDTO.getDescription());
        clickBase(btnSaveNewContact);
    }

    public boolean validateContactCreated(String phone) {


        return isElementByTextExistInTheList(textH3ContactList, phone);
    }

    public void openContactInfoByPhone(String phone) {
        clickBase(getPhoneNumberInContactsLocator(phone));

    }

    public void removeActiveContact() {
        clickBase(btnRemoveContact);
    }
    public void navigateToContactPage() {
        driver.navigate().to("https://telranedu.web.app/contacts");
    }
}
