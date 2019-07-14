
package suffix;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author bugra
 */
public class Node {
    public char sub;
    public String suffix;
    public int dep;
    public Node parent;
    public List<Node> child;
    
    public Node(){
        this.sub='*';
        this.child=new ArrayList<Node>();
        this.parent=null;
        this.suffix="";
        this.dep=0;
    }
    public Node(char sub, Node parent, String suffix, int dep){
       this.sub=sub;
       this.parent=parent;
       this.child=new ArrayList<Node>();      
       this.suffix=suffix+sub;
       this.dep=dep;
    }
 
    public void addChild(Node ch){
        this.child.add(ch);
    }
    
}
