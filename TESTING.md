# Testing Checklist
- Every future update this list should be modified if there is any major change to testing functionality
- Use a physical device, preferably on the latest android version
- Use emulator for older android versions

## Home Activity
- Verify that the buttons under "Calculators" direct to the appropriate activity
- Verify that the buttons under "Our apps" redirect to the correct Playstore listings
- Verify that all the menu options direct to their correct actions (see #Menu-Items for more)
## Color to Value Activity
- Ensure that the "Four Bands" options shows these dropdowns: First Number, Second Number, Multiplier, Tolerance
- Ensure that the "Five Bands" options shows these dropdowns: First Number, Second Number, Third Number, Multiplier, Tolerance 
- Ensure that the "Six Bands" options shows these dropdowns: First Number, Second Number, Third Number, Multiplier, Tolerance, Temp Coefficient
- For the First Number dropdown it must show the following options: Brown, Red, Orange, Yellow, Green, Blue, Violet, Gray, White
- For the Second/Third Number dropdowns it must show the following options: Black, Brown, Red, Orange, Yellow, Green, Blue, Violet, Gray, White
- For the Multiplier dropdown it must show the following options: Black, Brown, Red, Orange, Yellow, Green, Blue, Violet, Gray, White, Gold, Silver
- For the Tolerance dropdown it must show the following options: Brown, Red, Green, Blue, Violet, Gray, Gold, Silver
- For the Temp Coefficient dropdown it must show the following options: Black, Brown, Red, Orange, Yellow, Green, Blue, Violet, Gray
- Check to make sure that a resistance value appears when all items are selected for all 3 options
- Check to see if all the user selections persist after leaving and returning to the activity
- Check to see if the clearing selections persists after leaving and returning to the activity
## Value to Color Activity
- Ensure that the "Four Bands" and "Five Bands" options shows these dropdowns: Tolerance
- Ensure that the "Six Bands" options shows these dropdowns: Tolerance, Temp Coefficient
- When the resistance text box is empty and the calculate button is hit, make sure that the resistor and value are 'reset'
- When an invalid input is entered make sure the text box gets the red error and if the button is pressed that "Invalid Input" is shown
- When a user leaves with an invalid input entered make sure when they return it defaults to nothing
- For the Tolerance dropdown it must show the following options: Brown, Red, Green, Blue, Violet, Gray, Gold, Silver
- For the Temp Coefficient dropdown it must show the following options: Black, Brown, Red, Orange, Yellow, Green, Blue, Violet, Gray
## About Activity
- Ensure that the correct and most update version number and last updated on date are correct for the update
- Verify that the buttons under "Our apps" redirect to the correct Playstore listings
## Menu Items
- Share: Ensure that the values correctly pertain to the shareable text
- Feedback: Ensure that the tab opens an email with the email and subject auto-filled
- Clear Selections: Ensure that all selections that have been made are completely cleared and the screen is reset
- About This App: Ensure that the about activity is properly opened
## Miscellaneous Items
- Check the app with different size text fonts and display fonts to help ensure that everything is still visible and not cut off
