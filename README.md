# HostableChat
A small demo project and maybe future open source software

Note: Some of the frontend code is taken from CreativeTim. It was just adjusted for my needs.
Also please be aware that there are some difficulties with the Client.
I haven't figured out yet, why the JARs cannot be executed even though they are executable jars.
A lot of different errors occurr.

Now to the project:

The basic idea was to create a easy to deploy and easy to use Chat that is Open Source.
Check out the code, make your adjustments, build a jar or executable and have fun chatting.

Why? Some people want to be less dependent on the big companies just to have a chat tool.


Current state and limitations:

Until now there is a Client build in JavaFX and a Server build with Spring Boot.

## Server

If you build a jar with mvn clean package you can run the server anywhere you like.
Just make sure to configure all properties in the application.properties.
On startup you will notice that a default Admin account is created.
You can use it to log in to create more accounts.
In the Dashboard you can see a few stats. Some are actually gathered others are just dummy numbers.

Right now I haven't written Tests in any form or kind only because the overall idea still has to ripen a bit more.
Most of the classes consist of logic that creates something and logs results.

## Client

Even though I couldn't make an executable jar that works, you can start the client over your IDE with no problem.
The client login is already connected to the server.
It is possible to configure a URL under preferences. This configuration gets stored next to the jar but is not used yet.
After logging in you will see the basic idea of how the chat tool is supposed to look like.

You are able to add new channels (right now only on client side and not in the way it is done in the near future).
You can choose different channels (on every select the Chat-Area gets resetted at the moment)
You can chat. On every message you send you will receive a "Test Response" 5 seconds later.

Because most of the logic is just of displaying nature, so far no tests.
