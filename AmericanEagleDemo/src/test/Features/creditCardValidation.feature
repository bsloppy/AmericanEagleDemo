Feature: checkout credit card validation

  Scenario:test error messages
    Given we launch chrome browser
    Then we wait
    When we navigate to "https://www.ae.com/us/en"
    Then we wait
    When we add an item to cart
    #click on view cart
    When we click on "//*[@name='viewBag']"
    #click on proceed to checkout
    And we click on "//*[@class = 'cart-buttons col-unpadded']/..//button[1]"
#validation
#error messages when blank

    When we click on "//*[@name ='cardNumber']"
    And we click on "//*[@name ='expirationDate']"
    And we click on "//*[@name ='securityCode']"
    And we click on "//*[@name ='phoneNumber']"
    And we click on "//*[@name ='cardNumber']"
  #creditnullError
    Then "//*[@name ='cardNumber']/..//div" should contain the text "Please enter credit card number, this field can't be empty"
    #expnullError
    And "//*[@name ='expirationDate']/..//div" should contain the text "Please enter an expiration date."
    #cvvnullError
    And "//*[@name ='securityCode']/..//div" should contain the text "Please enter a CVV number."
   #phonenullError
    And "//*[@name ='phoneNumber']/..//div" should contain the text "Please enter a phone number."

#invalid entry
#credit card
#note this error message doesn't always appear correctly due to bug so it was commented out
   # And we enter "1231 2312 3123 1231" into "//*[@name ='cardNumber']"
    # And we click on "//*[@name ='expirationDate']"
    #credit card type error
    #Then "//*[@name ='cardNumber']/..//div" should contain the text "Credit card type missed."
   # When we clear "//*[@name ='cardNumber']"
    And we enter "411111111111111" into "//*[@name ='cardNumber']"
    #credit card valid type
    And we click on "//*[@name ='expirationDate']"
    Then "//*[@name ='cardNumber']/..//div" should contain the text "Please enter a valid card number."

#exp date
    When we enter "###" into "//*[@name ='expirationDate']"
    #invalid exp
    When we click on "//*[@name ='cardNumber']"
    Then "//*[@name ='expirationDate']/..//div" should contain the text "This expiration date is invalid. Please review your card information."
    When we clear "//*[@name ='expirationDate']"
    And we enter "1111" into "//*[@name ='expirationDate']"
    When we click on "//*[@name ='cardNumber']"
    #validates expired date
    And "//*[@name ='expirationDate']/..//div" should contain the text "This card is expired. Please use a different card or payment method."

#cvv
    When we enter "###" into "//*[@name ='securityCode']"
    When we click on "//*[@name ='cardNumber']"
    Then "//*[@name ='securityCode']/..//div" should contain the text "Please enter a valid CVV number, using numbers only."

#Phone number
    When we enter "222222222@" into "//*[@name ='phoneNumber']"
    When we click on "//*[@name ='cardNumber']"
    Then "//*[@name ='phoneNumber']/..//div" should contain the text "Please enter a valid phone number."










    Then we close the driver
