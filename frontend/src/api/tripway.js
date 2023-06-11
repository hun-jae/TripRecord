import { tripwayInstance } from "./index.js";

const api = tripwayInstance();

function listArticle(param, success, fail) {
  console.log(param.pgno);
  api
    .get(`/searchlist?pgno=${param.pgno}&keyword=${param.keyword}&search_code=${param.search_code}`)
    .then(success)
    .catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/write`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(bid, success, fail) {
  api.get(`/view?bid=${bid}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.post(`/update`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(bid, success, fail) {
  api.get(`/delete?bid=${bid}`).then(success).catch(fail);
}
function searchTripway(keyword, success, fail) {
  api.get(`/search?keyword=${keyword}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle, searchTripway };
