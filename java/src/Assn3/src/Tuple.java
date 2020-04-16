import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tuple<R,  T>
{

private R name;
private T index;

public Tuple(R r, T t)
{
    this.name = r;
    this.index = t;
}

public static void main(String[] args)
{
    List<Tuple<String, Integer>> tuples = new ArrayList<Tuple<String, Integer>>();
    // insert elements in no special order
    tuples.add(new Tuple<String, Integer>("Joe", 2));
    tuples.add(new Tuple<String, Integer>("May", 1));
    tuples.add(new Tuple<String, Integer>("Phil", 3));

    

}

public R getName()
{
    return name;
}

public void setName(R name)
{
    this.name = name;
}


public T getIndex()
{
    return index;
}

public void setIndex(T index)
{
    this.index = index;
}

}