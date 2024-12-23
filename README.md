# UofTrackMyGame
Final Project for CSC207 at the University of Toronto

## Table of Contents

- [Contributors](#contributors)
- [Project Goals](#project-goals)
- [Features](#features)
- [Installation](#installation)
- [Usage Guide](#usage-guide)
- [Feedbacks](#feedbacks)
- [Contributions](#contributions)
- [License](#license)


## Contributors
Frederick Chen - Pancraes, Bhavya Jain - bhavya-jain-364, Andrew Kim - akhkim, Kerri Wei - kerri-wei, John Zhang - ycz425


## Project Goals
UofTrackMyGame is a video game price searching application where users can search for games, and the application 
compares the prices across various platforms to display the best available deal at that moment.

This project was initially created to provide better access to gaming for all, especially those who find it financially
burdening to pay the full price for a game. Overall, UofTrackMyGame aims to allow people to 
save as much as possible on games. 

UofTrackMyGame not only provides the best prices across various video game platforms, but also allows users to search by
various filters, including the availability of sale and metacritic score. Furthermore, the application has a feature to 
"Track the game", where users can set a desired price for a game and input their email. Then, when that game goes below 
the set desired price, the application will send an email notification, preventing the user from having to constantly 
check the price of the game. Finally, users can also keep track of multiple games via a wishlist, or find similar games 
through the "Find Similar Games" feature.

## Features
- <b>Search Game by Title</b> 
  - Users can search for games by Title. Results are sorted by descending Deal Rating (Deal Rating is a score out of 10
  on how 'good' a deal is) 
- <b>Search Game by Filter and Sorting </b>
  - Users can search for games with the following filters:
    - Upper Bound for Price
    - Lower Bound for Price
    - Metacritic Score (rating out of 100 on how good the game is)
    - On Sale or Full Price
  - When searching by filter, users can choose to sort the results (ascending or descending) by the following filters:
    - Deal Rating 
    - Title 
    - Savings (percent of original price that the game is on sale for)
    - Price
    - Metacritic Score
- <b>Find similarly priced games</b>
  - After clicking open a game from the results page, there is a 'Find Similar Games' button that will search for 
    games of similar price 
- <b>Price Tracking of a specific game</b>
  - For a specific game, users can set a 'threshold price' and enter their email to be notified when the price of the 
    game drops below the threshold price
  - Adds to a Wishlist, where you can view all the games you are tracking
- <b>Wishlist</b>
  - Users can keep a list of games by adding them to their Wishlist
  - Users can access their Wishlist via the Search page

## Installation
1. Clone and open the project from GitHub by pasting the following line into the terminal (in VS Code or IntelliJ):
```text
git clone https://github.com/akhkim/UofTrackMyGame.git
```
3. Link Maven when prompted.
4. Navigate to ```src/main/java/app/Main.java``` and run

## Usage Guide
<b>Searching and Sorting Results</b>  
Running ```Main.java``` will open the Search screen. From here, users can choose to search by title or search by
filters. The results can be sorted by a variety of filters.  
![image](https://github.com/user-attachments/assets/e7fce931-a23d-41e8-8641-648f3b16f443)  

After clicking "Search by Title" or "Search by Filters", the Results screen will open. To switch between screens,
use the "Back" button at the bottom of the screen.  
![image](https://github.com/user-attachments/assets/d0989797-14f5-4891-acfc-3adea27ca7ae)  

Each panel of the Results screen can be clicked open to a Game Popup Screen.  The Game Screen displays the
thumbnail of the game, as well as other information. Users can also click "Find Similar Games" to find similarly
priced games. Finally, users can add the game to their wishlist with the "Add To Wishlist" button.  
![image](https://github.com/user-attachments/assets/b0db4559-a46c-49f0-9b4e-119ddfe84b37)  

The Game Screen displays the thumbnail of the game, as well as other information. Users can enter a price and their
email and click "Notify Me" to be notified when the price of the game drops below their specified price.
![image](https://github.com/user-attachments/assets/63c362eb-81da-4d94-b45d-4550c39da3ff)  

Lastly, users can view their wishlist by navigating to the Search Screen and click "Go to Wishlist". To remove
entries from the wishlist, navigate to ```src/main/java/data/wishlist.json``` and delete the entries. 
![image](https://github.com/user-attachments/assets/b90a0559-e74d-4368-8f86-ee6979e0a415)  


## Feedbacks
Make sure to read the following text on the qualities of good feedback first, then submit your feedback [here](https://forms.gle/hCCgtfJsUAzHo5sq5)!

5 Qualities of a good feedback:

1. Constructiveness
    1. Avoid general statements like "It's bad"; instead, provide details such as "The UI is difficult to navigate because the icons are not labeled."

2. Relevance
    1. The feedback should relate directly to the topic, product, or project.
    2. Avoid unrelated comments or feedback that does not address the purpose of the submission.

3. Actionability
    1. Provide a possible solution to the feedback
    2. Example: Instead of saying "I don’t like this", suggest "Adding a search function could improve usability."

4. Specificity
    1. Use clear and concise language that avoids ambiguity.
    2. Feedback must clearly describe the issue, feature, or experience.

5. Politeness and Respect
    1. Maintain a professional and respectful tone, even when discussing challenges.
    2. Feedback containing offensive or disrespectful language will not be accepted.

## Contributions
To make the process of contributions and updating smoother, we recommend the following:

1. Fork this project repository and clone it to your local machine. (Read more About Forks)
Before working on any changes, try to sync the forked repository to keep it up-to-date with the upstream repository.
2. On a new branch in your fork, work on a small focused change that only touches on a few files.
3. Run pre-commit and make sure all files have formatting fixed.
4. Package up a small bit of work that solves part of the problem into a Pull Request and send it out for review.
5. If you're lucky, we can merge your change into main without any problems. If there are changes to files you're working on, resolve them by:
    1. First try to rebase as suggested in these instructions.
    2. If rebasing feels too painful, merge as suggested in these instructions.
8. Once you've resolved conflicts (if any), finish the review and squash and merge your Pull Request.
9. Merge in your change and move on to a new issue or the second step of your current issue.

## License
This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.
