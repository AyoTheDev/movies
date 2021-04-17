# Breaking Bad Campus Tours

*This project retrofits an older sample project made by myself to satisfy the requirements of the task.*

This Android app project was made using Kotlin, it uses the breaking bad API found here: https://breakingbadapi.com/ that pretend to be campuses. It contains a map
button in the toolbar (which does nothing when clicked) and it also has a bottom navigation bar, where each item is selectable but the page does not change.

For dependency injection Dagger is used. On the UI side, we're using MVVM and the rest of the project uses clean architecture where it is split into the following
layers/modules:

* **App**, this is where we implement everything concerned with the UI using MVVM
* **Api**, this contains all the code for talking to the back-end/cloud services
* **Domain**, this layer contains the business logic and data model entities. The point of this layer is to have a standard set of objects that can be reused within
different projects

* A basic unit test has been written for the ViewModel where we test a mocked usecase, this test also uses a basic mock of the CampusDomain object returned by 
the use case.

* For data handling, we use a mutable list inside the `CampusListAdapter` which is updated via its `update()` method that takes an immutable list as a parameter.
This way we're sure that our list cannot be changed during run time after we've fetched it from the API with our `CharachterUseCase`.

* The project adheres to the SOLID OOP principles in the following way:
  
  * **Single Responsibility**
    * The `CharachterUseCase` is a good example of single responsibility as its one job is to retrieve data about the characters (or campuses). If we were to change 
    this class a valid reason might be that we want to return a filtered/queried list of characters (or campuses).

  * **Open Closed**
      * An example of this principle in the sample project is the `CoroutineContextProvider` which we use to provide thread contexts either to run on the UI or 
      in a background worker thread. We still need to specify thread contexts in our tests but we don't want to use those that are implemented by the original class
      so we extend a special case for tests called `TestContextProvider`
      
  * **Liskov Substitution**
      * Although not specifically created by myself and instead by the android framework, an example of this exists in our `MainViewModel` with its use of `LiveData`
      and `MutableLiveData`. We need to be able to expose LiveData to the `MainActivity` (the main view) and we also need to be able to modify or mutate this LiveData.
      So we have a private MutableLiveData `_campusListLiveData` for us to manipulate and then we expose it to the view with a public LiveData `campusLiveData` this
      way we can modify our data source without carelessly exposing it to the View.
      
  * **Interface Segregation**
      * Similar to the previous point, this project didn't give much scope to implement principle myself but the Retrofit library that is used to make
      network calls in the API module has us create the interface `EndPoints` and then the library generates the implementation.
  
  * **Dependency Inversion**
      * Following from the previous point, our abstraction `EndPoints` is dependent on the `ApiService`. We do not concretely implement it within the class itself.
      
- - - -

*Further points for consideration*

* The toolbar we're using isn't the most robust, and given more complex requirements would not be an appropiate approach. The most flexible method is to inflate
a fragment as a toolbar, this would require a little bit more boiler plate and set up but would let us do much fancier things, also a simpler approach would be 
to use an onverflow menu.

* For now the only consideration taken for debug/dev vs release/prod builds is that when a we're in a release build we have minify set to enabled so that our code
is obfusciated making it more difficult for any malicious reverse engineering attemps on the application
