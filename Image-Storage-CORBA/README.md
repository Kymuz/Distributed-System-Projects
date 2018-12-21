# Image Storage CORBA
The Server contain some stored image and the user could retrieve an image

# instllation 
1. ### Start the ORB server
   - orbd is another program that comes with the JDK, so we
     need to configure it as an External Tool in Eclipse with the
     following arguments
   - Inserte "-ORBInitialPort 1050 -ORBInitialHost localhost" AS an arguments in the argument section
2. ### Start the server application
   - Start the server application with the following command
     --> -ORBInitialPort 1050 -ORBInitialHost localhost
   - Run the server application,If it starts successfully ---> you should see the following output: ("Server is Running...") 
3. ### Start Client application
   - Start the Client application with the following command
     --> -ORBInitialPort 1050 -ORBInitialHost localhost
   - Run the server application,If it starts successfully ---> you should see the Gui

## For more detailed installation
- Please Refer to [CORBA-Eclipse Lecture](http://www-scf.usc.edu/~csci201/lectures/Lecture25/CORBA-Eclipse.pdf) 
