==================================
WIREFRAME CORRECTIONS
==================================
Login Page Instruction Changes
----------------------------------
1. Send an asynchronous POST request to http://dev3.apppartner.com/AppPartnerDeveloperTest/scripts/login.php
5. The only valid login is username: AppPartner password: qwerty

=================
INSTRUCTIONS
=================

1. Your Application must support Android 4.0+ Portrait Orientation only and target the latest API level.
2. You only need to support Android Phones, do not worry about Tablets or Android Wear.
3. Please make use of the starter project (AndroidProgrammerTest) and complete all sections.
4. Please take care of the bug(s) we left for you in the project as well.


Thank you and Good luck. - App Partner

====================
COMMON QUESTIONS
====================

Q:    Just wanted to ask can we use available GitHub libraries available to implement the task?
A:    Yes, as long as it is made apparent where the library/code that it was taken from.

============================================================================================================================================

Q:    For the Android test, applying the given font sizes (22pt, 19pt, 15pt etc...) to the Textview make text really big. 
      Is that the standard font sizes I should be applying or I can change it to use smaller font size.

A:    If the wireframe asks you to apply a size 22pt font, the TextView should use a font size of 22sp. 
      If this does not match the mockup, try applying 1/2 the recommended font size i.e.) 11sp instead of 22sp. 
      If that still does not look accurate, you should simply try to make it match the mockups as closely as possible.

============================================================================================================================================

Q:    From the chat part, the icon of each person was not provided in the asset folder.
A:    The icon of each person must be downloaded from our server. Take a look at the ChatData class and the avatarURL property.

============================================================================================================================================

Q:    My Network calls to your login.php endpoint are not working?
A:    Please make sure that you are sending us the POST data correctly. Take a look at the postman_reference.jpg image provided if you are confused.

============================================================================================================================================