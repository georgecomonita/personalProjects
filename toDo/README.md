# ToDoManagerApp (TDMA)

This app enables users to manage a basic to-do list. The following functionalities are implemented:
- Add
- Remove
- Update
- Display
- Generate a txt file which contains the items an user owns. The file has a specific format.
- Import a txt file inside the app. The content will be <b>parsed</b> and <b>validated</b>. If the validation succeeds a list of items will be added to the user

Two types of items can be created by the user:
- basic to-do item
- high priority to-do item

For the latter, a number of time units will be entered by the user. Once the high priority item is created, in the background, a <b>Runnable task</b> will constantly check if the deadline is still valid. When the deadline expired, a message will be printed to the console, notifying the user about this.

The main goal of this homework was to use and apply all the concepts you have learned so far:

- Classes/Objects
- OOP principles
- Collections
- Exceptions
- Working with files (I/O)
- Threads
