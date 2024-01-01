// mongo-init/init.js

db = db.getSiblingDB('books');

// Create user
db.createUser({
    user: "root",
    pwd: "example",
    roles: [
        { role: "readWrite", db: "books" }
    ]
});

for (let i = 0; i < 100000; i++) {
    db.bookCollection.insert({
        title: "Book " + i,
        description: "Description for book " + i,
        isbn: 100000000000 + i,
        price: Math.floor(Math.random() * 90) + 10,
        available: i % 2 === 0
    });
}

db.bookCollection.createIndex({ "isbn": 1 });
