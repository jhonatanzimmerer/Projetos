package cls.teste;

import cls.bd.GetBD;
import cls.obj.Momento;

public class testeGetBD {
    public void testeLoadAtivoMomento(){
        GetBD getBD = new GetBD();
        Momento momento = new Momento();
        momento = getBD.loadAtivoMomento("Ativo","ANIM3");
        System.out.println(momento.getAtivo());
    }
}
