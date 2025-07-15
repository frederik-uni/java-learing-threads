# Functions
- `sleep(?)` : Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds, subject to the precision and accuracy of system timers and schedulers. The thread does not lose ownership of any monitors.
- `getId()/threadId()`: Returns the identifier of this Thread. The thread ID is a positive long number generated when this thread was created. The thread ID is unique and remains unchanged during its lifetime.
- `isAlive()`: Tests if this thread is alive. A thread is alive if it has been started and has not yet terminated.
- `getName()`: Returns this thread's name.
- `start()`: Schedules this thread to begin execution. The thread will execute independently of the current thread. A thread can be started at most once. In particular, a thread can not be restarted after it has terminated.
- `join()`: Waits for this thread to terminate. An invocation of this method behaves in exactly the same way as the invocation
- `interrupt()`: Interrupts this thread. Unless the current thread is interrupting itself, which is always permitted, the checkAccess method of this thread is invoked, which may cause a SecurityException to be thrown. If this thread is blocked in an invocation of the wait(), wait(long), or wait(long, int) methods of the Object class, or of the join(), join(long), join(long, int), sleep(long), or sleep(long, int) methods of this class, then its interrupt status will be cleared and it will receive an InterruptedException. If this thread is blocked in an I/O operation upon an InterruptibleChannel then the channel will be closed, the thread's interrupt status will be set, and the thread will receive a java.nio.channels.ClosedByInterruptException.  If this thread is blocked in a java.nio.channels.Selector then the thread's interrupt status will be set and it will return immediately from the selection operation, possibly with a non-zero value, just as if the selector's wakeup method were invoked.
- `notifyAll()`: Wakes up all threads that are waiting on this object's monitor. A thread waits on an object's monitor by calling one of the wait methods.  The awakened threads will not be able to proceed until the current thread relinquishes the lock on this object. The awakened threads will compete in the usual manner with any other threads that might be actively competing to synchronize on this object; for example, the awakened threads enjoy no reliable privilege or disadvantage in being the next thread to lock this object.  This method should only be called by a thread that is the owner of this object's monitor. See the notify method for a description of the ways in which a thread can become the owner of a monitor.
- `wait()`: Causes the current thread to wait until it is awakened, typically by being notified or interrupted.  In all respects, this method behaves as if wait(0L, 0) had been called. See the specification of the wait(long, int) method for details.
- `notify()`: Wakes up a single thread that is waiting on this object's monitor. If any threads are waiting on this object, one of them is chosen to be awakened. The choice is arbitrary and occurs at the discretion of the implementation. A thread waits on an object's monitor by calling one of the wait methods. The awakened thread will not be able to proceed until the current thread relinquishes the lock on this object. The awakened thread will compete in the usual manner with any other threads that might be actively competing to synchronize on this object; for example, the awakened thread enjoys no reliable privilege or disadvantage in being the next thread to lock this object. This method should only be called by a thread that is the owner of this object's monitor. A thread becomes the owner of the object's monitor in one of three ways: By executing a synchronized instance method of that object. By executing the body of a synchronized statement that synchronizes on the object. For objects of type Class, by executing a static synchronized method of that class. Only one thread at a time can own an object's monitor.

# Objects
- `Semaphore(number)`: A counting semaphore. Conceptually, a semaphore maintains a set of permits. Each acquire blocks if necessary until a permit is available, and then takes it. Each release adds a permit, potentially releasing a blocking acquirer. However, no actual permit objects are used; the Semaphore just keeps a count of the number available and acts accordingly. Semaphores are often used to restrict the number of threads than can access some (physical or logical) resource. For example, here is a class that uses a semaphore to control access to a pool of items:
    - `acquire()`: Acquires a permit from this semaphore, blocking until one is available, or the thread is interrupted. Acquires a permit, if one is available and returns immediately, reducing the number of available permits by one. If no permit is available then the current thread becomes disabled for thread scheduling purposes and lies dormant until one of two things happens: Some other thread invokes the release method for this semaphore and the current thread is next to be assigned a permit; or Some other thread interrupts the current thread. If the current thread:has its interrupted status set on entry to this method; or is interrupted while waiting for a permit, then InterruptedException is thrown and the current thread's interrupted status is cleared.
    - `release()`: Releases a permit, returning it to the semaphore. Releases a permit, increasing the number of available permits by one. If any threads are trying to acquire a permit, then one is selected and given the permit that was just released. That thread is (re)enabled for thread scheduling purposes. There is no requirement that a thread that releases a permit must have acquired that permit by calling acquire. Correct usage of a semaphore is established by programming convention in the application.

# Flags
- `volatile`: Compiler und Prozessor signalisiert, dass der Wert einer Variablen sich jederzeit ändern kann, auch außerhalb des aktuellen Programms oder durch andere Threads.
  - für Sichtbarkeit einfacher Werte
- `synchronized` = Mutex

# Issues(Semaphore, conditionalVariable)
- dining philosophers problem(Mechaniker)
- Barrier
- Parkhaus
- consumer producer problem(Buffer)
- Peterson-Algorithmus?

# Begriffe
- `thread`:  leichtgewichtiger Ausführungsstrang, der innerhalb eines Prozesses läuft + gleicher Adressraum + jeder eigenen Stack
- `process`:  Instanz eines Programms, das gerade ausgeführt wird + eigenen Speicherbereich +  voneinander abgekapselt
- `spurious event`: unerwartetes Aufwachen eines wartenden Threads
- `OpenCL`: Framework wie cuda
- `Concurrenc`y: Aufgaben laufen wirklich gleichzeitig auf mehreren Kernen oder Prozessoren.
- `Condition Variables`: wait/notify/notifyAll
- `Deadlock`: zwei oder mehr Threads dauerhaft blockiert sind, weil jeder auf eine Ressource wartet, die vom anderen gehalten wird – und keiner gibt sie freiwillig wieder frei.
- `Mutex`:  Sperrmechanismus, der sicherstellt, dass immer nur ein Thread gleichzeitig auf einen kritischen Abschnitt oder gemeinsame Ressource zugreifen kann.
- `Semaphore`:  ist wie ein Zähler für freie Plätze
- `Nebenläufigkeit`: Aufgaben überlappen sich zeitlich, aber müssen nicht gleichzeitig laufen
- `reentrant`: mehrmals gleichzeitig aufgerufen werden kann
- `thread-safe`: kann sicher von mehreren Threads gleichzeitig verwendet werden kann/ keine Datenkorruption, Race Conditions oder anderen Nebenwirkungen