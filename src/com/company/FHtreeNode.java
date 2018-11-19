public class FHtreeNode<E> {
    // use protected access so the tree, in the same package,
    // or derived classes can access members
    protected FHtreeNode<E> firstChild, sib, prev;
    protected E data;
    protected FHtreeNode<E> myRoot;  // needed to test for certain error
    protected boolean deleted;

    public FHtreeNode(E d, FHtreeNode<E> sb, FHtreeNode<E> chld, FHtreeNode<E> prv, boolean del) {
        firstChild = chld;
        sib = sb;
        prev = prv;
        data = d;
        myRoot = null;
        deleted = del;
    }

    public FHtreeNode() {
        this(null, null, null, null, false);
    }

    public E getData() {
        return data;
    }

    // for use only by FHtree (default access)
    protected FHtreeNode(E d, FHtreeNode<E> sb, FHtreeNode<E> chld,
                         FHtreeNode<E> prv, FHtreeNode<E> root, boolean del) {
        this(d, sb, chld, prv, del);
        myRoot = root;
    }
}
