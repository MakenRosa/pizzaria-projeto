# JavaFXTeste — Pizzaria Topzera (Pedidos em JavaFX)

> Projeto **didático** em **JavaFX** que simula o **cadastro** e a **pesquisa** de pedidos de uma pizzaria.
> **Status:** conteúdo **legado** (últimos commits há **+2 anos**). Mantido para **documentação/arquivo**.

---

## Sumário

* [Visão Geral](#visão-geral)
* [Estrutura do Repositório](#estrutura-do-repositório)
* [Telas & Funcionalidades](#telas--funcionalidades)
* [Arquitetura](#arquitetura)
* [Como Executar](#como-executar)

  * [Opção A — JDK 8 (JavaFX embutido)](#opção-a--jdk-8-javafx-embutido)
  * [Opção B — JDK 17+ com OpenJFX](#opção-b--jdk-17-com-openjfx)
* [Avisos de Legado & Pontos de Atenção](#avisos-de-legado--pontos-de-atenção)
* [Melhorias Futuras (sugestões)](#melhorias-futuras-sugestões)
* [Licença](#licença)

---

## Visão Geral

Aplicação desktop feita com **FXML + Controllers** e estilos em **CSS**. A tela principal abre janelas para:

* **Cadastro de Pedido** (formulário com máscaras, opções e validações simples).
* **Pesquisa de Pedido** (lista mockada com filtro por texto).
* **Contadores** de vendas (hoje e total), com *popup* comemorativo ao atingir 200.

---

## Estrutura do Repositório

```
/
├─ JavaFXTeste/
│  ├─ build.xml                 # Script Ant (gera/roda via NetBeans/Ant; depende de nbproject)
│  ├─ manifest.mf               # Manifesto do JAR
│  └─ src/javafxteste/
│     ├─ JavaFXTeste.java       # Main (Application)
│     ├─ WindowManager.java     # Gerência de janelas (abre/fecha Stages)
│     ├─ TelaPrincipal.fxml     # Tela inicial
│     ├─ TelaPrincipalController.java
│     ├─ CadastroPedido.fxml
│     ├─ CadastroPedidoController.java
│     ├─ PesquisaPedido.fxml
│     ├─ PesquisaPedidoController.java
│     └─ style.css              # Estilos (ids/classes usados nas FXML)
├─ .gitignore                   # ignora /nbproject/private, /build, /dist
└─ LICENSE                      # MIT
```

---

## Telas & Funcionalidades

### Tela Principal

* **Menu & Botões**: abrir Cadastro/Pesquisa, sair do app.
* **Contadores**: “Vendidas Hoje” e “Total Vendidas”.
* **Popup**: ao `Total Vendidas == 200` exibe alerta informativo.

### Cadastro de Pedido

* **Tamanho** (Broto/Grande/Gigante), **Sabor** (com descrição automática), **Borda**, **Quantidade** (Spinner).
* **Bebida** (ComboBox).
* **Cliente**: nome (máscara/validação), telefone (máscara `(99)9 9999-9999`), data (`dd/mm/aaaa`), valor(es).
* **Pagamento**: Dinheiro, Pix, Crédito, Débito (ToggleGroup).
* **Ações**: **Cadastrar** (incrementa contadores da tela principal) e **Cancelar**.

### Pesquisa de Pedido

* **Filtro** por texto (nome, pedido ou detalhes).
* **Tabela** com colunas: Pedido, Cliente, Detalhes.
* Dados **mockados** em memória (sem banco).

---

## Arquitetura

* **FXML + Controllers**: separação de layout (FXML) e lógica (Java).
* **WindowManager**: carrega *FXML* e gerencia *Stages* abertos, com utilitário para fechar tudo ao sair.
* **Estilos (CSS)**: classes/ids aplicados nas FXML (`.background`, `.titulo`, `.btn_tela_principal`, etc.).
* **Main** (`JavaFXTeste.java`):

  * Carrega `TelaPrincipal.fxml`, retém referência ao `TelaPrincipalController` (getter estático).
  * No *close request*, finaliza janelas abertas e encerra a aplicação.

---

## Como Executar

> **Requisitos gerais**
>
> * **JavaFX 8** (JDK 8 Oracle) **ou** **JDK 17+** com **OpenJFX** instalado.
> * Um IDE com suporte a JavaFX (NetBeans/IntelliJ/Eclipse) **ou** linha de comando.

### Opção A — JDK 8 (JavaFX embutido)

1. Importe o projeto no **NetBeans** como “JavaFX com Ant”.

   > ⚠️ O `build.xml` importa `nbproject/build-impl.xml`. Se a pasta `nbproject/` não existir no seu clone, o Ant **não** compilará. O NetBeans recria essa pasta ao “abrir como projeto”.
2. Rode a classe principal: `javafxteste.JavaFXTeste`.

### Opção B — JDK 17+ com OpenJFX

1. Baixe/instale o **OpenJFX** (compatível com seu JDK).
2. Defina `PATH_TO_FX` apontando para a pasta `lib` do OpenJFX.
3. Compile e rode pela linha de comando (a partir de `JavaFXTeste/src`):

```bash
# Linux/macOS
export PATH_TO_FX=/caminho/para/javafx/lib

javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml \
      -d out $(find javafxteste -name "*.java")

cp javafxteste/*.fxml out/javafxteste/
cp javafxteste/style.css out/javafxteste/

java --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml \
     -cp out javafxteste.JavaFXTeste
```

```powershell
# Windows (PowerShell)
$env:PATH_TO_FX="C:\caminho\para\javafx\lib"

# Compile
Get-ChildItem -Recurse javafxteste\*.java | %{$_.FullName} |
  javac --module-path "$env:PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -d out @-

# Copiar recursos
Copy-Item javafxteste\*.fxml out\javafxteste\
Copy-Item javafxteste\style.css out\javafxteste\

# Run
java --module-path "$env:PATH_TO_FX" --add-modules javafx.controls,javafx.fxml `
     -cp out javafxteste.JavaFXTeste
```

> Dica: você pode converter para **Maven/Gradle** com OpenJFX para simplificar *build/run* em JDKs modernos.

---

## Avisos de Legado & Pontos de Atenção

* **Ant/NetBeans**: o `build.xml` depende de arquivos da pasta `nbproject/` (não versionada). Se ausentes, recrie abrindo no NetBeans ou migre para Maven/Gradle.
* **JavaFX 8 × OpenJFX**: o FXML usa namespace `javafx/8`. Em JDK 11+, use OpenJFX e `--module-path/--add-modules`.
* **Recursos (imagens)**: o `style.css` referencia ícones (ex.: `../images/add-pedido.png`) **não presentes** no repositório. Os botões ficam sem imagem.
* **Validação de valores**:

  * `applyValueMask` aceita **vírgula** (`,`) como separador decimal.
  * `validateValue` espera **ponto** (`.`). → **Inconsistente**; ao usar vírgula, a validação pode falhar.
* **fx\:id não utilizados**: alguns `@FXML` no `CadastroPedidoController` (ex.: `brotoRadioButton`) não estão conectados a ids do FXML (ids são `broto`, `grande`, `gigante`). Não quebra a execução porque os campos não são usados, mas ficam `null`.
* **Persistência**: não há banco/arquivo; tudo é em memória para fins didáticos.
* **Responsividade**: janelas com `setResizable(false)`.

---

## Melhorias Futuras (sugestões)

* **Build moderno**: migrar para **Maven** (plugin `javafx-maven-plugin`) ou **Gradle** (plugin `org.openjfx.javafxplugin`).
* **Recursos**: adicionar `/images/*` referenciados no CSS e empacotar como *resources*.
* **i18n/Locale**: alinhar **máscara/validação** de valores com `NumberFormat` de `pt-BR` (ou padronizar ponto/vírgula).
* **Validação**: mensagens de erro visuais (Labels/Tooltips) e realce de campos inválidos.
* **Domínio**: extrair um modelo `Pedido` próprio (fora do controller) e, se desejado, persistência (SQLite/JPA).
* **UX**: cálculo automático de **valor total**, formatação monetária, e botões desabilitados quando inválido.
* **Testes**: testes de unidade para formatação/validação e testes de UI (TestFX).

---

## Licença

Este projeto está sob **MIT License**. Veja `LICENSE` para detalhes.

---

> *“Projeto de estudo em JavaFX. Se for retomá-lo hoje, considere migrar para JDK 17+ com OpenJFX e configurar um build com Maven/Gradle para facilitar a execução.”*
