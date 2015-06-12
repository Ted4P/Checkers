import java.util.ArrayList;

    public class Node<Board> {
        private Board data;
        private Node<Board> parent;
        private ArrayList<Node<Board>> children;
        private double score;
        
        public Node(Board board){
        	this(board,null);
        }
        
        public Node(Board board, Node<Board> parent){
        	data = board;
        	this.parent = parent;
        	children = new ArrayList<Node<Board>>();
        }
        
        public void addChild(Board board){
        	children.add(new Node<Board>(board, this));
        }
        
        public void setScore(double score){ this.score = score;}
        public double getScore(){ return score;}
        public Node<Board> getParent(){ return parent;}
        public Board getBoard(){ return data;}
        public int getChildNum(){ return children.size();}
        public Node<Board> getChild(int index){ return children.get(index);}
    }
