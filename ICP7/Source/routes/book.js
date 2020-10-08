var express = require('express');
var router = express.Router();
var Book = require('../models/Book.js');

/* GET ALL BOOKS */
router.get('/', function (req, res, next) {
  Book.find(function (err, products) {
    if (err) return next(err);
    res.json(products);
  });
});

/* GET SINGLE BOOK BY ID */
router.get('/:id', function (req, res, next) {
  Book.findById(req.params.id, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* SAVE BOOK */
router.post('/', function (req, res, next) {
  Book.create(req.body, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* UPDATE BOOK */
router.put('/:id', function (req, res, next) {
  const bookId = req.params.id;
  console.log(bookId);
  Book.findByIdAndUpdate(bookId, req.body, (err,book) => {
    if (err) return next(err);
    res.json(book);
  })
});

/* DELETE BOOK */

router.delete('/:id', function (req, res, next) {
  const bookId = req.params.id;
  Book.findByIdAndDelete(bookId, (err,book) => {
    if (err) return next(err);
    res.json({success: true})
  })
});

module.exports = router;
