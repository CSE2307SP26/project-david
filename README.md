# project26

## Team Members:

* Nachuan Ding
* Haoxuan Yuan
* Xinyu Pan
* Shiyuan Zhang

## User stories

1. A bank customer should be able to deposit into an existing account. (Shook)
2. A bank customer should be able to withdraw from an account. (Shiyuan)
3. A bank customer should be able to check their account balance. (Haoxuan)
4. A bank customer should be able to view their transaction history for an account. (Haoxuan)
5. A bank customer should be able to create an additional account with the bank. (Shiyuan)
6. A bank customer should be able to close an existing account. (Xinyu)
7. A bank customer should be able to transfer money from one account to another. (Nachuan)
8. A bank adminstrator should be able to collect fees from existing accounts when necessary. (Nachuan)
9. A bank adminstrator should be able to add an interest payment to an existing account when necessary. (Xinyu)

## What user stories do you intend to complete next iteration?
In the upcoming iteration, We plan to further optimize the account management system. Specifically, We will expand the verification scope to prevent users from being restricted. Additionally, We will also consider improving the user interaction of the menu system, such as providing clearer prompts.

## Is there anything that you implemented but doesn't currently work?
All the functions we have implemented are currently running as expected, and all the unit tests have been successfully passed.


## What commands are needed to compile and run your code from the command line?
To compile and run the project from the command line, the following steps are required:

First, compile all source and test files:

javac -cp "test-lib/*" -d bin src/main/*.java src/test/*.java

Then, run the unit tests using JUnit:

java -cp "bin;test-lib/*" org.junit.platform.console.ConsoleLauncher --scan-class-path

To run the application interactively:

java -cp "bin" main.MainMenu

OR we can click the run button in the Mainmenu.java file in the IDE to start the application.