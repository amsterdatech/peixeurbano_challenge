# Avaliação para Engenheiro de Software Android

## Para esse teste ser avaliado, você precisará:
* criar um repositório no Github para este projeto;
* adicionar como colaboradores do projeto os usuarios: [dbonates](https://github.com/dbonates), [guilhermesan](https://github.com/guilhermesan) e [dnassuncao](https://github.com/dnassuncao);
* notificar a empresa avisando que o teste está disponível para verificação.

### Objetivo

O objetivo do teste é desenvolver um app que liste ofertas disponíveis próximas de um usuário e, conforme sua movimentação e após um período de tempo atualize as ofertas na interface.

### O que avaliamos

**Atente para este item, pois é justamente como o time de engenharia vai olhar e avaliar o que você fez!**

Arquiteturas utilizadas, código limpo e comentado o suficiente, organização e modularização, uso de recursos nativos versus bibliotecas externas, e todas decisões tomadas relacionadas à melhor exposição dos seus talentos em desenvolvimento mobile.

Não exigimos um teste _pixel perfect_, contudo uma interface visual bem replicada e feita de forma consistente será considerada como um diferencial.


### Requisitos do teste

* Utilize Android Studio IDE.
* Opcional Kotlin.
* Seja clean e teste sempre que possível.
* Bom gosto crítico visual fará muita diferença.
* Crie um projeto privado no Github e faça um push. * Crie uma pasta com o último apk release gerado e nos comunique por email.

### Instruções

Para resolver este problema será necessário:

* Uma splash com 5 segundos de cooldown com ícone de carregamento para apresentação das suas identificações. (nome, email, profissão, …)

* Uma tela com uma lista de ofertas onde cada oferta clicável deve expor um thumbnail da sua imagem, o título da oferta, a descrição da oferta e o preço.

* A lista deve ser chamada através da seguinte api pública:

```
https://raw.githubusercontent.com/PeixeUrbano/desafio-android/master/api/deals.json
```

#### Referência para as chaves relevantes e outras informações:

- Thumbnail: `response.images[k].image`
- Título da oferta: `response.partner.name`
- Descrição: `response.short_title`   /   Preço: `response.sale_price`
- A lista de ofertas deve ser rolável e o parâmetro offset deve aumentar de `10` em `10` de forma a não impactar a fluidez da rolagem.