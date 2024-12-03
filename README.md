# UofTrackMyGame
Final Project for CSC207 at the University of Toronto. 

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
Frederick Chen, Bhavya Jain, Andrew Kim, Kerri Wei, John Zhang


## Project Goals
UofTrackMyGame is a video game price searching application where users can search for games, and the application 
compares the prices across various platforms to display the best available deal at that moment.

This project was initially created to provide better access to gaming for all, especially those who find it financially
burdening to pay the full price for a game. In particular, we wanted users to be able to search across various filters,
such as Metacritic score; get notified by email when the price of a game is decreased; and save games for later 
reference through a Wishlist. Overall, UofTrackMyGame aims to allow people to save as much as possible on games. 

## Features
- <b>Search Game by Title</b> 
  - Users can search for games by Title. 
- <b>Search Game by Filter </b>
  - Users can search for games with the following filters:
    - Upper Bound for Price
    - Lower Bound for Price
    - Metacritic Score (rating out of 100 on how good the game is)
    - On Sale or Full Price
- <b> Sorting Results</b>
  - When searching by filter, users can choose to sort the results (ascending or descending) by the following filters:
    - Deal Rating (rating out of 10 on how good the deal is)
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
  
## Installation [TODO: add videos]
1. Clone and open the project from GitHub by pasting the following line into the terminal (in VS Code or IntelliJ):
```text
git clone https://github.com/akhkim/UofTrackMyGame.git
```
3. Link Maven when prompted.
4. Navigate to ```src/main/java/app/Main.java``` and run

<b>Troubleshooting and common issues</b>  
[TODO: go over linking maven here]

## Usage Guide
<b>Searching and Sorting Results</b>  
Running ```Main.java``` will open the Search screen. From here, users can choose to search by title or search by 
filters. The results can be sorted by a variety of filters.  
![image](https://github.com/user-attachments/assets/eac72b3f-2d62-4944-a4d5-288d4606ac1f)

After clicking "Search by Title" or "Search by Filters", the Results screen will open.  
![image](https://github.com/user-attachments/assets/4d1ee5ac-01d5-4ddd-ae67-eb88cceaec3f)

Each panel of the Results screen can be clicked open to a Game Popup Screen.  The Game Screen displays the 
thumbnail of the game, as well as other information. Users can also click "Find Similar Games" to find similarly 
priced games. Finally, users can add the game to their wishlist with the "Add To Wishlist" button.  
![image](https://github.com/user-attachments/assets/5ea004f0-207b-4c30-8d7c-6a75ef703513)

The Game Screen displays the thumbnail of the game, as well as other information. Users can enter a price and their 
email and click "Notify Me" to be notified when the price of the game drops below their specified price. 
![image](https://github.com/user-attachments/assets/619534a7-d0cb-4f33-a162-71fce3d79f05)  

To switch between screens, users can click "Back" to be directed to the home screen.  
![image](https://github.com/user-attachments/assets/9cc3a321-ae03-497d-ab92-87e2b1aa3248)  

Lastly, users can view their wishlist by navigating to the Search Screen and click "Go to Wishlist".  
[TODO: INSERT PHOTOS]:


## Feedback
Make sure to read the following text on the qualities of good feedback first, then submit your feedback [here](https://forms.gle/hCCgtfJsUAzHo5sq5)!

<b>5 Qualities of good feedback:</b>

1. Constructiveness
    1. Avoid general statements like "It's bad." Instead, provide details such as "The UI is difficult to navigate 
   because the icons are not labeled."

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
