package net.btnz.pri.java.myjavabasetest.asm;
/**
 * Created by zhangsongwei on 2017/2/3.
 */

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/2/3 16:02
 */
public class AopMethodVisitor extends MethodVisitor implements Opcodes {
    public AopMethodVisitor(int api, MethodVisitor mv){
        super(api, mv);
    }
    @Override
    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, "net/btnz/pri/java/myjavabasetest/asm/AopInteceptor", "before", "()V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        if(opcode >= IRETURN && opcode <= RETURN) {
            this.visitMethodInsn(INVOKESTATIC, "net/btnz/pri/java/myjavabasetest/asm/AopInteceptor", "after", "()V", false);
        }
        super.visitInsn(opcode);
    }
}
