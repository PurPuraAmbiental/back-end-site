public  class EnderecoEmpresa {
    //ADCIONANDO ATRIBUTOS DO MODELO LOGICO
    private int nCdEnderecoEmpresa;
    private String cBairro;
    private String clogradouro;
    private String cEstado;
    private String cCidade;
    private String cCep;
    private String cComplemento;
    private String cNumero;

    //ADICONANDO METODO CONSTRUTOR
    public EnderecoEmpresa(int nCdEnderecoEmpresa, String cbairro, String clogradouro, String cEstado, String cCidade, String cComplemento, String cCep, String cNumero){
        this.nCdEnderecoEmpresa = nCdEnderecoEmpresa;
        this.cBairro = cbairro;
        this.clogradouro = clogradouro;
        this.cEstado = cEstado;
        this.cCidade = cCidade;
        this.cComplemento = cComplemento;
        this.cCep = cCep;
        this.cNumero = cNumero;
    }

    //ADICIONANDO GETTRS
    public String getcBairro() {
        return bairro;
    }

    public int getnCdEnderecoEmpresa() {
        return nCdEnderecoEmpresa;
    }

    public String getcCep() {
        return cCep;
    }

    public String getcCidade() {
        return cCidade;
    }

    public String getcEstado() {
        return cEstado;
    }

    public String getcComplemento() {
        return cComplemento;
    }

    public String getClogradouro() {
        return clogradouro;
    }

    //ADICIONANDO OS SETTERS NECESSARIOS
    public void setcBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setcCep(String cCep) {
        this.cCep = cCep;
    }

    public void setcCidade(String cCidade) {
        this.cCidade = cCidade;
    }

    public void setcComplemento(String cComplemento) {
        this.cComplemento = cComplemento;
    }

    public void setcEstado(String cEstado) {
        this.cEstado = cEstado;
    }

    public void setClogradouro(String clogradouro) {
        this.clogradouro = clogradouro;
    }

    //ADICIONANDO METODO TO STRING
    public String toString(){
        return "\ncodigo de endereco da empresa"+this.nCdEnderecoEmpresa+
                "\nrua: "+this.clogradouro+
                "\nnumero: "+this.cNumero+
                "\ncidade: "+this.getcCidade+
                "\nbairro: "+this.getBairro()+
                "\nestado: "this.getcEstado()+
                "\ncomplemento: "this.getcComplemento()+
                "\ncep: "+this.getcCep();
    }
}