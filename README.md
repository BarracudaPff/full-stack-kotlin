run H2
`docker run --name my-h2 -p 8082:8082 -d buildo/h2database`

jdbc: `jdbc:h2:my-h2:8082./data/db`
