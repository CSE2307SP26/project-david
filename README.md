


## Team Members:

- Nachuan Ding
- Haoxuan Yuan
- Xinyu Pan
- Shiyuan Zhang

## User stories

1. A bank customer should be able to deposit into an existing account. (Shook)
2. A bank customer should be able to withdraw from an account. (Shiyuan)
3. A bank customer should be able to check their account balance. (Haoxuan)
4. A bank customer should be able to view their transaction history for an account. (Haoxuan)
5. A bank customer should be able to create an additional account with the bank. (Shiyuan)
6. A bank customer should be able to close an existing account. (Xinyu)
7. A bank customer should be able to transfer money from one account to another. (Nachuan)
8. A bank administrator should be able to collect fees from existing accounts when necessary. (Nachuan)
9. A bank administrator should be able to add an interest payment to an existing account when necessary. (Xinyu)
10. A bank customer should be able to set a withdraw limit for an account. (Haoxuan)
11. A bank customer should be able to rename their account. (Haoxuan)
12. A bank administrator should be able to freeze an account. (Shiyuan)
13. A bank customer should be able to loan money from the bank. (Shiyuan)
14. A bank customer should be able to check the account summary.(Xinyu)
15. A bank customer should be able to have a minimum balance.(Xinyu)
16. A bank customer should be able to check their remaining loan balance. (Nachuan)
17. A bank customer should be able to pay off a loan. (Nachuan)
18. A bank administrator should be able to unfreeze an account. (Shiyuan)
19. A bank administrator should be able to add interest to an existing loan. (Shiyuan)
20. A bank customer should be able to set a deposit limit for an account. (Haoxuan)
21. A bank customer should be able to set a withdraw count limit for an account. (Haoxuan)
22. A bank customer should be able to set a savings goal for an account. (Nachuan)
23. A bank customer should be able to view loan payment history for an existing loan. (Nachuan)
24. A bank customer should be able to view the most recent transaction for an account. (Xinyu)
25. A bank customer should be able to view the total number of transactions associated with an account. (Xinyu)
## What user stories do you intend to complete next iteration?

At this point, the core user stories planned for this project have been completed. In the final stage, we mainly focused on polishing the existing features, improving code organization, and making sure the program runs correctly with passing unit tests.

## Is there anything that you implemented but doesn't currently work?

All the functions we have implemented are currently running as expected, and all the unit tests have been successfully passed.

## What commands are needed to compile and run your code from the command line?

Compile:
`javac -cp "test-lib/*" -d bin src/main/*.java src/test/*.java`

Run tests:
`java -cp "bin;test-lib/*" org.junit.platform.console.ConsoleLauncher --scan-classpath`

Run the application:
`bash runApp.sh`
