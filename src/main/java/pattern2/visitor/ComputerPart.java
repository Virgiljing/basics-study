package pattern2.visitor;

public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
