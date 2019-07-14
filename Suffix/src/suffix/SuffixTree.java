package suffix;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author bugra
 */
public class SuffixTree {
    
    public List<String> strList=new ArrayList<>();
    public static List<Node> nodes=new ArrayList<>();

    public SuffixTree(String str, Node root){
        
        nodes.add(root);
        for (int i = 0; i < str.length(); i++) {
           strList.add(str.substring(i));
        }
        for (int i = 0; i < str.length(); i++) {            
            root=ekleSuffix(strList.get(i), root);
        }
    }

    public static Node ekleSuffix(String str, Node root){
        Node temp=root;
        Node temp2=new Node();
        for (int i = 0; i < str.length(); i++) {   
           if(temp.child.isEmpty()){ 
               
               if(temp==root){
                  Node sub=new Node(str.charAt(i), temp, temp.suffix, temp.dep+1);
                  nodes.add(sub);
                  temp.addChild(sub);
                  temp=sub;
               }else{
                  Node sub=new Node(str.charAt(i), temp, temp.suffix, temp.dep);
                  nodes.add(sub);
                  temp.addChild(sub);
                  temp=sub;
               }                        
           }
           else{   
                int j=0;
                for (int k = 0; k < temp.child.size(); k++) 
                {   
                    if(temp.child.get(k).sub==str.charAt(i)){                    
                           temp=temp.child.get(k);                        
                           j=1;
                           temp2=temp;           
                    }
                }       
                if(j==0){                   
                      
                      Node sub=new Node(str.charAt(i), temp,temp.suffix,1);
                      nodes.add(sub);
                      temp.addChild(sub);
                      temp=sub;
                      while(temp2.parent!=null){
                      temp2.dep++;
                      temp2=temp2.parent; 
                      }    
                }             
           }      
        }       
        while(temp.parent!=null){
            temp=temp.parent;           
        }       
       return temp;
    }
    public static boolean ara(String target, Node root){
       List<Node> rootChildren = root.child;
       int sayac=0;
        for (int i = 0; i < target.length(); i++) {
            for (int j = 0; j < rootChildren.size(); j++) 
            {
                if(target.charAt(i)==rootChildren.get(j).sub)
                {
                    sayac++;
                    rootChildren=rootChildren.get(j).child;                
                    break;   
                } 
            }
            if(sayac==0)
            {
                return false;
            }
            sayac=0;
        }      
    return true;
    }
    public static Node cokUzun(Node root)
    {      
        Node s=new Node();    
        for (int i = 0; i < nodes.size(); i++) {        
            if(nodes.get(i).dep>1){  
                if(nodes.get(i).suffix.length()>s.suffix.length()){  
                  s=nodes.get(i);
                }  
            }     
        }    
        return s;
    }
    public static List cokTekrar(Node root)
    {
        int sayac=0;
        List<Node> encok=new ArrayList<>();   
        for (int i = 0; i < nodes.size(); i++) {        
                if(nodes.get(i).dep>sayac){
                    sayac=nodes.get(i).dep;
                }  
        }   
        if(sayac>1){
            for (int i = 0; i < nodes.size(); i++) {        
                if(nodes.get(i).dep==sayac){
                    encok.add(nodes.get(i));
                 }
            }
        }
      return encok;
    }  
    
}
