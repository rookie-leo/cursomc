# Projeto desenvolvido durante curso de Modelagem Conceitual do professor Nélio Alves
<br><br>
## *BACK END*
Nesse projeto será construido login, cadastro de usuário, fluxo completo de navegação de carrinho de compras, fluxo de fechamento de pedido, tela de perfil de usuário com envio de foto tanto pela câmera quanto pela galeria.<br>
Para isso, será implementada uma API Rest usando Java com Spring Boot e banco de dados MySQL. O orm será feito com Hibernate, e o envio de e-mail com SMTP da Google. O armazenamento de imagens será feito com storage S3 da Amazon.<br>
## *FRONT END*
O front end será desenvolvido utilizando Ionic, com a arquitetura MVC do Angular, os componentes visuais do Ionic, Angular reactive forms, navegação, interceptors, local storage, paginação com infinite scroll, loading e refresher.<br>
Versão: Ionic 3 e Angular 5
<br>
## *Anotações*
° Durante a população do banco de dados H2 no arquivo data.sql, é visto a partir da linha 58, que precisei inserir dados na tabela pagamento antes de inserir dados nas tabelas pagamento_com_cartao e pagamento_com_boleto. Antes de fazer isso estava recebendo <b>JdbcSQLIntegrityConstraintViolationException</b>.<br>
Link do commit: https://github.com/rookie-leo/cursomc/commit/cbf830df98b09c975c65f2812746acfb2f3955b7

<br><br>
Link do curso na Udemy: https://www.udemy.com/course/spring-boot-ionic/
<br>
Link para o repositório do professor no GitHub: https://github.com/acenelio
