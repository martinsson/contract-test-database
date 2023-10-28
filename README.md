# Setup

Start mongodb locally

    docker run --rm -p 27017:27017 --name mongo_contract_testing -d mongo

Run existing tests in IDE or `mvn test`

# Instructions

1. Make the contract test (using abstract test class)
2. Due to the failing test implement in-memory repository
3. Evolve the repository with a method getByAuthor(), that returns a list of books by author
4. Improve performance of getById()
