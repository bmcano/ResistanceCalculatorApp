# Testing Checklist
- For every update this list should be modified if there has been any major change
- Use a physical device, preferably on the latest android version
- Use emulator for older android versions

## New Installation
- Ensure that the default selection is the "Four Bands" option for both calculators
- Ensure that everything is cleared when the app opens for the first time on a new device (or after storage wipe)

## Home Activity
- Verify that the buttons under "Calculators" direct to the appropriate activity
- Verify that the buttons under "Our apps" redirect to the correct Playstore listings

## Color to Value Activity
- Ensure that the "Three Bands" option shows these dropdowns: First Number, Second Number, Multiplier
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
- Ensure that the "Three Bands" only shows the resistance text box and the units dropdown
- Ensure that the "Four Bands" and "Five Bands" options shows these dropdowns: Tolerance
- Ensure that the "Six Bands" options shows these dropdowns: Tolerance, Temp Coefficient
- When the resistance text box is empty and the calculate button is hit, make sure that the resistor and value are 'reset'
- When an invalid input is entered make sure the text box gets the red error and if the button is pressed that "Invalid Input" is shown
- When a user leaves with an invalid input entered make sure when they return it defaults to nothing
- For the Tolerance dropdown it must show the following options: Brown, Red, Green, Blue, Violet, Gray, Gold, Silver
- For the Temp Coefficient dropdown it must show the following options: Black, Brown, Red, Orange, Yellow, Green, Blue, Violet, Gray

## About Activity
- Ensure that the "App version" and "Last updated on" sections are correct for the update
- Verify that the "View the IEC 60062  standard" button redirects to the website explaining all the values
- Verify that the buttons under "Our apps" redirect to the correct Playstore listings

## Menu Items
**Check and verify the actions for each activity that has a menu.**
- Share: Ensure that the values correctly pertain to the shareable text
- Feedback: Ensure that the tab opens an email with the email and subject auto-filled
- Clear Selections: Ensure that all selections that have been made are completely cleared and the screen is reset
- About This App: Ensure that the about activity is properly opened

## Miscellaneous Items
- Check the app with different size text fonts and display fonts to help ensure that everything is still visible and not cut off
- Check the app in both light and dark mode to ensure there aren't any odd or out of place colors