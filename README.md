STAR WARS – API
Versão 0
A API descrita a seguir pode ser utilizada para manipular as informações dos planetas presentes nos filmes do Star Wars. Serão disponibilizadas as seguintes funcionalidades:
Listar Todos os Planetas
Retorna a lista de todos os planetas.
Exemplo de Requisição
GET http://localhost:8080/v0/planets/
Exemplo de Resposta
HTTP Status 200 (OK)
[
	{
		_id: “4cdfb11e1f3c000000007822”
		nome: “Alderaan”,
		climate: “temperate”,
		terrain: “grasslands, mountains"
		moviesAppearances: 2,
		idSwapi: 2
	},
	{
		_id: “507f191e810c19729de860ea”	
		name: “Tatooine”,
		climate: “arid”,
		terrain: “desert"
		moviesAppearances: 5,
		idSwapi: 1
	}
]
Listar Planeta pelo Identificador
Retorna o planeta pelo Identificador.
Exemplo de Requisição
GET http://localhost:8080/v0/planets/4cdfb11e1f3c000000007822
Exemplos de Resposta
HTTP Status 200 (OK)
{
	_id: “4cdfb11e1f3c000000007822”
	nome: “Alderaan”,
	climate: “temperate”,
	terrain: “grasslands, mountains"
	moviesAppearances: 2,
	idSwapi: 2
}
HTTP Status 404 (Registro não encontrado)
Listar Planeta pelo Nome
Retorna o planeta pelo Nome.
Exemplo de Requisição
GET http://localhost:8080/v0/planets/search?name=Alderaan
Exemplos de Resposta
HTTP Status 200
{
	id: “4cdfb11e1f3c000000007822”
	nome: “Alderaan”,
	climate: “temperate”,
	terrain: “grasslands, mountains"
	moviesAppearances: 2,
	idSwapi: 2
}
HTTP Status 404 (Registro não encontrado)
Criar Planeta
Cria um planeta novo na base de dados.
Exemplo de Requisição
POST http://localhost:8080/v0/planets/
{
	nome: “Yavin IV”,
	climate: “temperate, tropical”,
	terrain: “jungle, rainforests"
}
Exemplos de Resposta
HTTP Status 201 (Criado)
{
	_id: “4cdfb11e1f3c000000007822”
	nome: “Alderaan”,
	climate: “temperate”,
	terrain: “grasslands, mountains"
	moviesAppearances: -1,
	idSwapi: 0
}

HTTP Status 400 (Requisição errada)
{
    "timestamp": "2019-04-27T00:57:37.148+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "defaultMessage": "Climate is required",
            "field": "climate",
        },
        {
            "defaultMessage": "Terrain is required",
            "field": "terrain",
        }
    ],
    "message": "Validation failed for object='planets'. Error count: 2",
    "path": "/planets/"
}
Mensagens de Validação no caso de uma Requisição Errada
Name is required
Terrain is required
Climate is required

Excluir Planeta pelo Identificador
Exclui um planeta pelo identificador.
Exemplo de Requisição
DELETE http://localhost:8080/v0/planets/4cdfb11e1f3c000000007822
Exemplos de Resposta
HTTP Status 200 (OK)
HTTP Status 404 (Registro não encontrado)
