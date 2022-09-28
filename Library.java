/* Sam Dosi
 * Dr. Steinberg
 * COP3330 Spring 2022
 * Programming Assignment 3
 */
class Book
{
    // Book Class Attributes
    private String title;
    private String author;
    private double cost;
    Book nextptr;
    //overload construction
    public Book(String title, String author, double cost)
    {
        this.title = title ;
        this.author = author ;
        this.cost = cost;
    }
    // making a deep copy
    public Book(Book old)
    {
        this.title = old.title;
        this.author = old.author;
        this.cost = old.cost;
        this.nextptr = old.nextptr;
    }
    // accessor methods
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public double getCost() {
        return cost;
    }
}
public class Library {
    // class attributes
    Book first ;
    int total;
    public Library(){
        this.first = null;
        this.total = 0;
    }
    public Library(Library old_ptr){
        System.out.println("Copying your Library collection.");
        // copy total
       this.total = old_ptr.total;
       // make a new node for the head of link list
       Book tmp =new Book(old_ptr.first);
       // update the head node
       this.first = tmp;
       // find the next node
       Book adder=old_ptr.first.nextptr;
       while (adder != null){
           tmp.nextptr=new Book(adder);
           tmp=tmp.nextptr;
           adder=adder.nextptr;
       }

    }
    public void add(String title, String author, double cost)
    {
        // if link list does not exist create one
        if(first == null)
        {
            first = new Book(title, author, cost);
            total++;
            return;
        }
        // if link list is full return
        if(full())
        {
            System.out.println("Your library is full.");
            return;
        }
        Book tmp = first ;
        // find the next place to insert the new node
        while (tmp.nextptr != null)
            tmp = tmp.nextptr;
        tmp.nextptr = new Book(title,author,cost);
        total++;
        return;
    }
    public void display()
    {
        Book tmp=first;
       while (tmp !=null) {
           System.out.println("Title: " + tmp.getTitle());
           System.out.println("Author: " + tmp.getAuthor());
           System.out.println("Cost: " + tmp.getCost());
           tmp = tmp.nextptr;
       }
    }
    // check to see if link list is full
    public boolean full()
    {
        return (total == 5);
    }
    public void reverse()
    {
        // if list is empty return
        if(first == null) return;
        // if there is only one node return
        if(first.nextptr == null) return;
        Book prev = null;
        Book current = first;
        Book next = null;
        // perform the reverse
        while (current != null) {
            next = current.nextptr;
            current.nextptr = prev;
            prev = current;
            current = next;
        }
        // update the head
        first = prev;
    }
    public void remove(String title)
    {
        // Store head node
        Book temp = first, prev = null;

        // if key is located in the head and head is not null return
        if (temp != null && temp.getTitle() == title) {
            first = temp.nextptr; // Changed head
            total--;
            return;
        }

        // search in the link list for the key that we are looking for
        while (temp != null && temp.getTitle() != title) {
            prev = temp;
            temp = temp.nextptr;
        }

        // if key was not found then return
        if (temp == null)
            return;

        // update the pointers
        prev.nextptr = temp.nextptr;
        total--;

    }
    // clear the library object
    public void clearLibrary()
    {
        this.first = null;
        this.total=0;
    }
    // check to see if the list is empty
    public boolean empty()
    {
        return (this.total == 0);
    }
    public String getTitle(Book temp)
    {
       return temp.getTitle();
    }
    public Book search(String title)
    {
        Book tmp = first;
        // search for the key that we are looking for
        while (tmp != null)
        {
            // found
            if(tmp.getTitle() == title){
                return tmp;}
            tmp = tmp.nextptr;
        }
        // not found
        return null;

    }

}
