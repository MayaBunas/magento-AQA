package magento.tests;

import org.junit.jupiter.api.Test;

public class HappyFlowTest extends TestBase {

   @Test
   public void testHappyFlow() {
      openApp();
      search("Primo Endurance Tank");
      openItemOnPage("Primo Endurance Tank");
      selectItemWithParams ("XL", "Yellow");
      addToCart();
      openCheckout();
      fillRandomShippingAddress();
      closeApp();
   }
}
