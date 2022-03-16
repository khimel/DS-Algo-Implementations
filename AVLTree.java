//Students : 
//	Mohammad Khimel , ID : 207001702
//	Nada Rayan , ID : 

/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 *
 */

public class AVLTree {
	private IAVLNode root = new AVLNode();
	private IAVLNode min = root;
	private IAVLNode max = root;
	

  /**
   * public boolean empty()
   *
   * returns true if and only if the tree is empty
   *
   */
	
	
  public boolean empty() {
	  if (this.root.getKey() == -1){
		  return true;
	  }
    return false; 
  }

 /**
   * public String search(int k)
   *
   * returns the info of an item with key k if it exists in the tree
   * otherwise, returns null
   */
  public String search(int k)
  {
	  IAVLNode node =  Nodesearch( k, root);
	  if(node == null) {
		  return null;
	  }
	 else {
		 return node.getValue();
	 }
  }
 
//////////Helping function!//////////
  /**
  *
  *  public IAVLNode  Nodesearch(int k,IAVLNode node)
  *
  * returns pointer to the node that should be deleted if it is in the tree
  * otherwise, returns null
  */
  public IAVLNode  Nodesearch(int k,IAVLNode node) {//new recursive function
	  if (!node.isRealNode()) {
		  return null;
	  }
	  else if (node.getKey()== k){
		  return node;// node.getValue(); 
	  }
	  else if(node.getKey() > k) {
		  return Nodesearch( k, node.getLeft());
		   }
	  else { return Nodesearch( k, node.getRight());
		  
	  }
	  
  }

  /**
   * public int insert(int k, String i)
   *
   * inserts an item with key k and info i to the AVL tree.
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
   * returns -1 if an item with key k already exists in the tree.
   */
   public int insert(int k, String i) {
	   
		  
	   IAVLNode newNode = new AVLNode(k,i);
	   if(this.empty()) {
		   this.min = newNode;
		   this.max = newNode;
	   }
	   else {
		   if (k< this.min.getKey()) {
			   this.min = newNode;
		   }
		   if (k>this.max.getKey()) {
			   this.max = newNode;
		   }
	   }
	  
	   return insertNode(newNode);
   }
   ////////// Helping function!//////////
   /**
    * public int insertNode(IAVLNode newNode)
    * updates the tree if rebalancing operations were made.
    * the tree must remain valid (keep its invariants).
    * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
    */
	   public int insertNode(IAVLNode newNode) {
	   if(search( newNode.getKey())!= null) {
		   return -1;
	   }
	   if (this.empty()) {
		   
		   this.root = newNode;
		   return 0;
	   }
	   else {
		   int counter = 0;
		   IAVLNode pointer = InsertTree( this.root,newNode);///where we inserted the new node + does insertion without rotations
		   while(pointer.isRealNode()) {////going up doing rotations!!
			   int oldHeight = pointer.getHeight();
			   pointer.setHeight(Math.max(pointer.getLeft().getHeight(), pointer.getRight().getHeight()) +1); /// set height
			   int BF = getBF(pointer);
			   if(Math.abs(BF)<2 & pointer.getHeight()== oldHeight) {
				   return counter;
			   }
			   else if(Math.abs(BF)<2 & pointer.getHeight()!= oldHeight) {
				   pointer = pointer.getParent();
			   }
			   else { ///////rotation
				   if(BF==2) {
					   if(getBF(pointer.getLeft()) == 1){// LL
						   RotateRight(pointer);
						   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
						   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
						   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize()+1);
						   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum() +pointer.getKey());////new nada
						   counter++;
					   }
					   else {//LR
						  IAVLNode nodey = pointer.getLeft();
						  RotateLeft(nodey);
						  nodey.getParent().setSubtreeSum(nodey.getSubtreeSum());/// new nada and mhamad - nada forgot
						  nodey.getParent().setSubtreeSize(nodey.getSubtreeSize());
						  nodey.setSubtreeSize(nodey.getLeft().getSubtreeSize() + nodey.getRight().getSubtreeSize() +1);
						  nodey.setSubtreeSum(nodey.getLeft().getSubtreeSum() +nodey.getRight().getSubtreeSum() + nodey.getKey());////new nada
						   
						  RotateRight(pointer);
						  pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
						  pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
						  pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize() +1);
						  pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum() +pointer.getKey());////new nada
						  counter+=2;
					   }
				   }
				   else {////BF =-2
					   if(getBF(pointer.getRight())==-1) {/// RR					 
						   RotateLeft(pointer);
						   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
						   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
						   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() + pointer.getRight().getSubtreeSize() + 1);
						   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum() +pointer.getKey());////new nada
						   counter++;
					   }
					   else {//RL
						   
						   IAVLNode nodey = pointer.getRight();
						   RotateRight(nodey);
						   nodey.getParent().setSubtreeSum(nodey.getSubtreeSum());/// new nada and mhamad - nada forgot
						   nodey.getParent().setSubtreeSize(nodey.getSubtreeSize());
						   nodey.setSubtreeSize(nodey.getLeft().getSubtreeSize() +nodey.getRight().getSubtreeSize()+1);
						   nodey.setSubtreeSum(nodey.getLeft().getSubtreeSum() +nodey.getRight().getSubtreeSum() +nodey.getKey());////new nada
						   
						   RotateLeft(pointer);
						   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
						   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
						   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize()+1);
						   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum() +pointer.getKey());////new nada
						   counter+=2;
					   }
				   } 
			   }
		   }
		   return counter;
	   }
   }

   
//////////Helping function!//////////
/**
*
*  public IAVLNode InsertTree(IAVLNode node ,IAVLNode newNode)
*
* inserts newNode the the tree with root node.
* returns parent of the new node
*/
	   public IAVLNode InsertTree(IAVLNode node ,IAVLNode newNode) {//new recursive function /////// true! proved 
		   
			  if (!node.isRealNode()) {

				  if (newNode.getKey() >node.getParent().getKey()) {
					  node.getParent().setRight(newNode);///inserting
					  newNode.setParent(node.getParent());
					  return  node.getParent();
				  }
				  else {
					  node.getParent().setLeft(newNode);
					  newNode.setParent(node.getParent());
					  return  node.getParent();
				  }
			  }
			  
			  else if(node.getKey() > newNode.getKey()) {
				  node.setSubtreeSize(node.getSubtreeSize() +1);
				  node.setSubtreeSum(node.getSubtreeSum() + newNode.getKey());//// new line- nada
				 return InsertTree( node.getLeft(),newNode);
				   }
			  else { 
				  node.setSubtreeSize(node.getSubtreeSize() +1);
				  node.setSubtreeSum(node.getSubtreeSum() + newNode.getKey());//// new line- nada
				  return InsertTree( node.getRight(),newNode);
				  
			  }
			  
		  }
//////////Helping function!//////////
/**
*
*  public void RotateLeft(IAVLNode P_node) 
*
* Implements right rotation to tree with root P_node.
* 
*/
   public void RotateLeft(IAVLNode P_node) {
	   IAVLNode temp_n = P_node.getRight(); 
	   IAVLNode parent =  P_node.getParent();
	   P_node.setRight(P_node.getRight().getLeft());
	   P_node.getRight().setParent(P_node);
	   
	   temp_n.setLeft(P_node);
	   if (!parent.isRealNode()) {
		   this.root = temp_n;
		   temp_n.setParent(new AVLNode());
		   temp_n.getParent().setRight(temp_n);
		   
	   }
	   else if (temp_n.getKey()> parent.getKey()) {///parent is smaller
		   parent.setRight(temp_n);
		   temp_n.setParent(parent);
		  
	   }
	   else {//parent is bigger
		   parent.setLeft(temp_n); 
		   temp_n.setParent(parent);
	   }
	   P_node.setParent(temp_n);
	   P_node.setHeight(Math.max(P_node.getLeft().getHeight(), P_node.getRight().getHeight())+1);
	   P_node.getParent().setHeight(Math.max(P_node.getHeight(),  P_node.getParent().getRight().getHeight())+1);
   }
//////////Helping function!//////////
/**
*
*  public void RotateLeft(IAVLNode P_node) 
*
* Implements left rotation to tree with root P_node.
* 
*/
   
   public void RotateRight(IAVLNode P_node) {
	   IAVLNode temp_n = P_node.getLeft(); 
	   IAVLNode parent =  P_node.getParent();
	   P_node.setLeft(P_node.getLeft().getRight());
	   P_node.getLeft().setParent(P_node); ///////// new 
	   temp_n.setRight(P_node);
	   if (!parent.isRealNode()) {
		   
		   this.root = temp_n;
		   temp_n.setParent(new AVLNode());
		   temp_n.getParent().setRight(temp_n);
		   
		   
	   }
	   else if (temp_n.getKey()> parent.getKey()) {///parent is smaller
		   parent.setRight(temp_n);
		   temp_n.setParent(parent);
		  
	   }
	   else {//parent is bigger
		   parent.setLeft(temp_n); 
		   temp_n.setParent(parent);
	   }
	   P_node.setParent(temp_n);
	   P_node.setHeight(Math.max(P_node.getLeft().getHeight(), P_node.getRight().getHeight()) +1);
	   P_node.getParent().setHeight(Math.max(P_node.getHeight(),  P_node.getParent().getLeft().getHeight())+1);
   }
   public int getBF(IAVLNode node) {////////////// returns balance factor 
		
		return node.getLeft().getHeight() - node.getRight().getHeight();
	}

  /**
   * public int delete(int k)
   *
   * deletes an item with key k from the binary tree, if it is there;
   * the tree must remain valid (keep its invariants).
   * 
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
   * returns -1 if an item with key k was not found in the tree.
   */
   public int delete(int k) {

	   if(search(k)== null) {
		   return -1;
	   }
	   if(k==this.min.getKey()) { // find the successor
		   if(this.min.getRight().isRealNode() && !this.min.getLeft().isRealNode()) { // if has right subtree
		//	   this.min = subMin(this.min.getRight());
		   }
		   else{
			   IAVLNode succz = this.min.getParent();
			   IAVLNode x = this.min;
			   while(succz.isRealNode() && x == succz.getRight() ) {
				   x=succz;
				   succz = succz.getParent();
			   }
			   this.min = succz;
		   }
	   }
	   
	   if(k==this.max.getKey()) { // find the predecessor
		   if(this.max.getLeft().isRealNode()) { // if has left subtree
			   this.max = subMax(this.max.getLeft());
		   }
		   else{
			   this.max = this.max.getParent();
		   }
	   }
	   
	   
	   int counter = 0;
	   IAVLNode pointer = deleteSearch(root,k);
	   if(pointer == null) {
		   return -1;
	   }
	   if(!pointer.isRealNode()){
		   pointer = pointer.getParent();
	   }
	   
	   while(pointer.isRealNode()) {////going up doing rotations!!
		   int oldHeight = pointer.getHeight();	 
		   if(k >pointer.getKey()) {
			   pointer.setHeight(Math.max(pointer.getLeft().getHeight(), pointer.getRight().getHeight() ) +1); /// set height
		   }
		   else {
			   pointer.setHeight(Math.max(pointer.getLeft().getHeight(), pointer.getRight().getHeight()) +1); /// set height
		   }
		   int BF = getBF(pointer);
		   if(Math.abs(BF)<2 & pointer.getHeight()== oldHeight) {
			   pointer = pointer.getParent();
		   }
		   else if(Math.abs(BF)<2 & pointer.getHeight()!= oldHeight) {
			   pointer = pointer.getParent();
		   }
		   else { ///////rotation
			   if(BF==2) {
				   if(getBF(pointer.getLeft()) == 1 || getBF(pointer.getLeft()) ==0){// LL
					   RotateRight(pointer);
					   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
					   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
					   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize()+1);
					   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum()+pointer.getRight().getSubtreeSum()+pointer.getKey());////new nada
					   counter++;
				   }
				   else {//LR
						  IAVLNode nodey = pointer.getLeft();
						  RotateLeft(nodey);
						  nodey.getParent().setSubtreeSum(nodey.getSubtreeSum());/// new nada and mhamad - nada forgot
						  nodey.getParent().setSubtreeSize(nodey.getSubtreeSize());
						  nodey.setSubtreeSize(nodey.getLeft().getSubtreeSize() + nodey.getRight().getSubtreeSize() +1);
						  nodey.setSubtreeSum(nodey.getLeft().getSubtreeSum() +nodey.getRight().getSubtreeSum() + nodey.getKey());////new nada
						   
						   RotateRight(pointer);
						   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
						   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
						   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize() +1);
						   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum() +pointer.getKey());////new nada
						   counter+=2;
				   }
			   }
			   else {////BF =-2
				   if(getBF(pointer.getRight())==-1 || getBF(pointer.getRight())==-0) {/// RR
					   RotateLeft(pointer);
					   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
					   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
					   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() + pointer.getRight().getSubtreeSize() + 1);
					   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum()+pointer.getKey());////new nada
					   counter++;
				   }
				   else {//RL
					   IAVLNode nodey = pointer.getRight();
					   RotateRight(nodey);
					   nodey.getParent().setSubtreeSum(nodey.getSubtreeSum());/// new nada and mhamad - nada forgot
					   nodey.getParent().setSubtreeSize(nodey.getSubtreeSize());
					   nodey.setSubtreeSize(nodey.getLeft().getSubtreeSize() +nodey.getRight().getSubtreeSize()+1);
					   nodey.setSubtreeSum(nodey.getLeft().getSubtreeSum() +nodey.getRight().getSubtreeSum() +nodey.getKey());////new nada
					   
					   RotateLeft(pointer);
					   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
					   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
					   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize()+1);
					   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum() +pointer.getKey());////new nada - mhamad added it nada forgot here
					   counter+=2;
				   }
			   } 
		   }
	   }
	return counter;
   }
   //overrRide for deleting in subtree only
   public int delete(IAVLNode node,int k) {
	   if(search(k)== null) {
		   return -1;
	   }

	   
	   int counter = 0;
	   IAVLNode pointer = deleteSearch(node,k);
	   if(pointer == null) {
		   return -1;
	   }
	   if(!pointer.isRealNode()){
		   pointer = pointer.getParent();
	   }
	   while(pointer.getKey()!= node.getParent().getParent().getKey()) {////going up doing rotations!!
		   int oldHeight = pointer.getHeight();
		   if(k > pointer.getKey()) {
			   pointer.setHeight(Math.max(pointer.getLeft().getHeight(), pointer.getRight().getHeight()) +1); /// set height
		   }
		   else {
			   pointer.setHeight(Math.max(pointer.getLeft().getHeight(), pointer.getRight().getHeight()) +1); /// set height
		   }
		   int BF = getBF(pointer);
		   if(Math.abs(BF)<2 & pointer.getHeight()== oldHeight) {
			   pointer = pointer.getParent();
		   }
		   else if(Math.abs(BF)<2 & pointer.getHeight()!= oldHeight) {
			   pointer = pointer.getParent();
		   }
		   else { ///////rotation
			   if(BF==2) {
				   if(getBF(pointer.getLeft()) == 1 || getBF(pointer.getLeft()) ==0){// LL
					   RotateRight(pointer);
					   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
					   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
					   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize()+1);
					   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum()+pointer.getRight().getSubtreeSum()+pointer.getKey());////new nada
					   counter++;
				   }
				   else {//LR
						  IAVLNode nodey = pointer.getLeft();
						  RotateLeft(nodey);
						  nodey.getParent().setSubtreeSum(nodey.getSubtreeSum());/// new nada and mhamad - nada forgot
						  nodey.getParent().setSubtreeSize(nodey.getSubtreeSize());
						  nodey.setSubtreeSize(nodey.getLeft().getSubtreeSize() + nodey.getRight().getSubtreeSize() +1);
						  nodey.setSubtreeSum(nodey.getLeft().getSubtreeSum() +nodey.getRight().getSubtreeSum() + nodey.getKey());////new nada
						   
						   RotateRight(pointer);
						   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
						   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
						   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize() +1);
						   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum() +pointer.getKey());////new nada
						   counter+=2;
				   }
			   }
			   else {////BF =-2
				   if(getBF(pointer.getRight())==-1 || getBF(pointer.getRight())==-0) {/// RR
					   RotateLeft(pointer);
					   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
					   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
					   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() + pointer.getRight().getSubtreeSize() + 1);
					   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum()+pointer.getKey());////new nada
					   counter++;
				   }
				   else {//RL
					   IAVLNode nodey = pointer.getRight();
					   RotateRight(nodey);
					   nodey.getParent().setSubtreeSum(nodey.getSubtreeSum());/// new nada and mhamad - nada forgot
					   nodey.getParent().setSubtreeSize(nodey.getSubtreeSize());
					   nodey.setSubtreeSize(nodey.getLeft().getSubtreeSize() +nodey.getRight().getSubtreeSize()+1);
					   nodey.setSubtreeSum(nodey.getLeft().getSubtreeSum() +nodey.getRight().getSubtreeSum() +nodey.getKey());////new nada
					   
					   RotateLeft(pointer);
					   pointer.getParent().setSubtreeSum(pointer.getSubtreeSum());/// new nada and mhamad - nada forgot
					   pointer.getParent().setSubtreeSize(pointer.getSubtreeSize());
					   pointer.setSubtreeSize(pointer.getLeft().getSubtreeSize() +pointer.getRight().getSubtreeSize()+1);
					   pointer.setSubtreeSum(pointer.getLeft().getSubtreeSum() +pointer.getRight().getSubtreeSum() +pointer.getKey());////new nada - mhamad added it nada forgot here
					   counter+=2;
				   }
			   } 
		   }
	   }
	return counter;
   }
//////////Helping function!//////////
/**
*
*  public IAVLNode  deleteSearch(IAVLNode node, int k) 
*
* returns a pointer to the node to be deleted if it is in the tree.
* otherwise returns null.
* updates the field sum and size
* 
*/
  
   public IAVLNode  deleteSearch(IAVLNode node, int k) {/// find the deleted node and takes care of size! 
	   // return pointer to deleted node and takes care of sum
	   if (!node.isRealNode()) {
			  return null;
		  }
	   if(node.getKey() > k) {
		   node.setSubtreeSize(node.getSubtreeSize()-1);
		   node.setSubtreeSum(node.getSubtreeSum()- k);/// new nada
		   return deleteSearch(node.getLeft(), k); //// was nodeSearch
		}
	   else if (node.getKey() < k){
			node.setSubtreeSize(node.getSubtreeSize()-1);
			 node.setSubtreeSum(node.getSubtreeSum()- k);/// new nada
			return deleteSearch(node.getRight(), k); /// here too
		}
	   else { /// found node to be deleted
			  if (!node.getRight().isRealNode() || !node.getLeft().isRealNode()) { /// if node has no right child or left child
				  IAVLNode tmp = new AVLNode();
				  if (tmp.getKey() == node.getRight().getKey()) {
					  tmp = node.getLeft();
				  }
				  else {
					  tmp = node.getRight();
				  }
				   /// no child case -- just cut off the node (make it virtual)
				  if(!tmp.isRealNode()) {
					  tmp = node;
					  node.setKey(-1);
					  node.setVal(null);
					  node.setHeight(-1);
					  node.setLeft(null);
					  node.setRight(null);
					  node.setSubtreeSize(-1);
					  node.setSubtreeSum(0);/////////////////////////////////////////////// new nada
					  return node;
					//  node.setParent(tmp.getParent());
				  }
				  else { ///tmp is real node -- one child case
					  node.setKey(tmp.getKey());
					  node.setVal(tmp.getValue());
					  node.setHeight(tmp.getHeight()); 
					  node.setLeft(tmp.getLeft());
					  node.getLeft().setParent(node);
					  node.setRight(tmp.getRight());
					  node.getRight().setParent(node);
					  node.setSubtreeSize(tmp.getSubtreeSize());
					  node.setSubtreeSum(tmp.getSubtreeSum());//- node.getKey());/// new nada  
					  return node;
				  }
			  }
			  else { // two children -- copy successor key and val to node , and go delete the successor
				  IAVLNode succ = subMin(node.getRight());
				  node.setSubtreeSum(node.getSubtreeSum()- node.getKey());/// not sure! nada
				  node.setKey(succ.getKey());
				  node.setVal(succ.getValue());
				  node.setSubtreeSize(node.getSubtreeSize() - 1);
				  delete(node.getRight(),succ.getKey());
				  node.setHeight(Math.max(node.getLeft().getHeight(), node.getRight().getHeight()) + 1);
				  
			  }
		  }
	return node;
	 }  
   
//////////Helping function!//////////
/**
*
*  public IAVLNode subMin(IAVLNode node)
*
* returns the node with the minimum key tree with root node
* 
*/  
   public IAVLNode subMin(IAVLNode node) {
	   while (node.getLeft().isRealNode()) {
		   node = node.getLeft();
	   }
	   return node;
   }
//////////Helping function!//////////
/**
*
*  public IAVLNode subMax(IAVLNode node)
*
* returns the node with the maximum key tree with root node
* 
*/  
   public IAVLNode subMax(IAVLNode node) {
	   while (node.getRight().isRealNode()) {
		   node = node.getRight();
	   }
	   return node;
   }

   /**
    * public String min()
    *
    * Returns the info of the item with the smallest key in the tree,
    * or null if the tree is empty
    */
   public String min()
   {
	   if (this.min!=null){
		   return this.min.getValue();
	   }
	   return null;

   }

   /**
    * public String max()
    *
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty
    */
   public String max()
   {
	   if (this.max!=null){
		   return this.max.getValue();
	   }
	   return null;
   }

  /**
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   */
  public int[] keysToArray()/// in order
  {
	  int[] answerz = new int[this.size()];
	  inOrder(this.root,answerz,0);
	  return answerz;
  }
//////////Helping function!//////////
/**
*
*   public  void inOrder(IAVLNode node,int[] answerz, int i) 
*
*  implements in order scanning for tree with root node
* returns an updated array answerz with sorted keys of the tree
*/  
  public  void inOrder(IAVLNode node,int[] answerz, int i) 
	  { 
	        if (!node.isRealNode()) 
	            return; 
	  
	        inOrder(node.getLeft(),answerz,i); 
	        answerz[node.getLeft().getSubtreeSize() + i] = node.getKey();
	        inOrder(node.getRight(),answerz,node.getLeft().getSubtreeSize() + i+1); 
	    }

  ///////////////////////////////////

  /**
   * public String[] infoToArray()
   *
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
   */
  public String[] infoToArray()
  {
	  String[] answerz = new String[this.size()];
	  inOrder(this.root,answerz,0);
	  return answerz;
  }
//////////Helping function!//////////
/**
*
*   public  void inOrder(IAVLNode node,int[] answerz, int i) 
*
*  implements in order scanning for tree with root node
* returns an updated array answerz with value sorted according to keys of the tree
*/  
  public  void inOrder(IAVLNode node,String[] answerz, int i) 
	  { 
	        if (!node.isRealNode()) 
	            return; 
	  
	        inOrder(node.getLeft(),answerz,i); 
	        answerz[node.getLeft().getSubtreeSize() + i] = node.getValue();
	        inOrder(node.getRight(),answerz,node.getLeft().getSubtreeSize() + i+1); 
	    }

   /**
    * public int size()
    *
    * Returns the number of nodes in the tree.
    *
    * precondition: none
    * postcondition: none
    */
   public int size()
   {
	   return root.getSubtreeSize();
   }
   
     /**
    * public int getRoot()
    *
    * Returns the root AVL node, or null if the tree is empty
    *
    * precondition: none
    * postcondition: none
    */
   public IAVLNode getRoot()
   {
	   if(this.empty()) {
		   return null;
	   }
	   return root;
   }
   ////////////////// new func for testing // remember to delete ////////////////////////////////////////////////////
   public IAVLNode minNode() {
	   if (this.empty()) {
		   return null;
	   }
	   return this.min;
   }
     /**
    * public string select(int i)
    *
    * Returns the value of the i'th smallest key (return null if tree is empty)
    * Example 1: select(1) returns the value of the node with minimal key 
	* Example 2: select(size()) returns the value of the node with maximal key 
	* Example 3: select(2) returns the value 2nd smallest minimal node, i.e the value of the node minimal node's successor 	
    *
	* precondition: size() >= i > 0
    * postcondition: none
    */   
   public String select(int i)
   {
	   if(this.empty()) {
		   return null;
	   }
	   return selectRec(root,i); 
   }
   public String selectRec(IAVLNode node , int i) {
	   if(!node.isRealNode()) {
		   return null;
	   }
	   int r = node.getLeft().getSubtreeSize() +1;
	   
	   if(i==r) {
		   return node.getValue();
	   }
	   else if(i<r) {
		   return selectRec(node.getLeft(),i);
	   }
	   else {
		   return selectRec(node.getRight(),i-r);
	   }
   }
   /**
    * public int less(int i)
    *
    * Returns the sum of all keys which are less or equal to i
    * i is not neccessarily a key in the tree 	
    *
	* precondition: none
    * postcondition: none
    */   
   public int less(int i)
   {
	   if(this.empty()) {
		   return 0;
	   }
	   IAVLNode myNode =Nodesearch(i, root);//pointer to the node ////// NEW MHAMAD
	   if(myNode != null) {// i in the tree
			  int s = myNode.getLeft().getSubtreeSum() + i;
			  IAVLNode y = myNode;
			  while(y != root) {
				  
				  if(y == y.getParent().getRight()) {//my node is right child,
					  s = s + y.getParent().getLeft().getSubtreeSum() + y.getParent().getKey();// sum parent and parent left child
				  }
				  y = y.getParent();
			  }
			  return s;
		  }
	   else {// i not in the tree :( 
			  IAVLNode myNodez = new AVLNode(i,Integer.toString(i));
			  InsertTree( this.root,myNodez);			  
			  int s = myNodez.getLeft().getSubtreeSum() + i ; /// new +i
			  IAVLNode y = myNodez;
			  while(y != root) {
				  if(y == y.getParent().getRight()) {//my node is right child,
					  s = s + y.getParent().getLeft().getSubtreeSum() + y.getParent().getKey();// sum parent and parent left child
				  }
				  y = y.getParent();
			  }
			  delete(i);
			  return s-i;
		  }
   } 
   

	/**
	   * public interface IAVLNode
	   * ! Do not delete or modify this - otherwise all tests will fail !
	   */
	public interface IAVLNode{
		public void setKey(int key);//new function
		public void setVal(String val);//new function
		public int getKey(); //returns node's key (for virtuval node return -1)
		public String getValue(); //returns node's value [info] (for virtuval node return null)
		public void setLeft(IAVLNode node); //sets left child
		public IAVLNode getLeft(); //returns left child (if there is no left child return null)
		public void setRight(IAVLNode node); //sets right child
		public IAVLNode getRight(); //returns right child (if there is no right child return null)
		public void setParent(IAVLNode node); //sets parent
		public IAVLNode getParent(); //returns the parent (if there is no parent return null)
		public boolean isRealNode(); // Returns True if this is a non-virtual AVL node
		public void setSubtreeSize(int size); // sets the number of real nodes in this node's subtree
		public int getSubtreeSize(); // Returns the number of real nodes in this node's subtree (Should be implemented in O(1))
		public void setHeight(int height); // sets the height of the node
	    public int getHeight(); // Returns the height of the node (-1 for virtual nodes)
	    public int getSubtreeSum();/// returns the sum of all keys in the subtree
	    public void setSubtreeSum(int i);// sets the sum of the node
	}

   /**
   * public class AVLNode
   *
   * If you wish to implement classes other than AVLTree
   * (for example AVLNode), do it in this file, not in 
   * another file.
   * This class can and must be modified.
   * (It must implement IAVLNode)
   */
  public class AVLNode implements IAVLNode{
	  private String info;
	  private int key;
	  private AVLNode left;
	  private AVLNode right;
	  private AVLNode parent;
	  private int size;
	  public int height;
	  private int sum;
	  
	  ///////////// constructor/////////////
	  public AVLNode () {///default
		  this.key = -1;
		  this.height = -1;
		  this.size = 0;
		  this.sum= 0;
	  }
	  public AVLNode (int k,String info) {
		  this.key = k;
		  this.info = info;
		  this.right = new AVLNode();
		  this.right.parent = this;
		  this.left = new AVLNode();
		  this.left.parent = this;
		  this.parent = new AVLNode();
		  this.parent.right = this;
		  this.parent.left = this;
		  this.size = 1;
		  this.sum = this.key;
		  
	  }
	  
	  public int getKey()
		{
			return this.key; 
		}
		public String getValue()
		{
			return this.info; 
		}
		public void setLeft(IAVLNode node)
		{
			this.left =  (AVLNode) node;
		}
		public IAVLNode getLeft()
		{
			return this.left; 
		}
		public void setRight(IAVLNode node)
		{	
			this.right =(AVLNode) node; 
		}
		public IAVLNode getRight()
		{	
			return this.right;
		}
		public void setParent(IAVLNode node)
		{	
			this.parent= (AVLNode) node;
		}
		public IAVLNode getParent()
		{
			return this.parent;
		}
		// Returns True if this is a non-virtual AVL node
		public boolean isRealNode()
		{
			if (this.key==-1) {
			return false;} 
			else {
				return true;
			}
		}
		public void setSubtreeSize(int size) {
			this.size = size;
		}
		public int getSubtreeSize()
		{	
			if(this.isRealNode()) {
			return this.size;
			}
			return 0;
		}
		
		@Override
		public void setHeight(int height) {
			this.height = height;
			
		}
		@Override
		public int getHeight() {
			if(this.isRealNode()) {
				return this.height;
			}
			return -1;
		}
		
		public void setKey(int key) {
			this.key = key;
			
		}
		
		public void setVal(String val) {
			this.info = val;
		}
	  
	 
	  /**
	   * returns 0 if key k is not found in the tree with root node
	   * else number begger than 0
	   
	   */  
		public int getSubtreeSum() {
			return this.sum;
		}
		
		public void setSubtreeSum(int sum) {
			this.sum = sum;
		}
		
  }

}







