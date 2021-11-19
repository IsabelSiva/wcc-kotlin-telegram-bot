package bots

import bots.Lista
class ListaDao {
    private val listaDao = hashMapOf(
        0 to Lista(id =0, item = "arroz", quantidadeItem = 4),
        1 to Lista(id =1, item = "leite", quantidadeItem = 10),
        2 to Lista(id =2, item = "cuscuz", quantidadeItem = 8),
        3 to Lista(id =3, item = "sabão", quantidadeItem = 32)

    )

    var lastId: AtomicInteger = AtomicInteger(listaDao.size - 1)

    fun getAll() = listaDao

    fun getActualLocation(): Lista? {
        val copy = listaDao
        val random = (1..listaDao.size).random()
        return copy[random-1]
    }

    fun save(item: String, quantidadeItem: Int){
        val id = lastId.incrementAndGet()
        listaDao.put(id, Lista(
            item = item,
            quantidadeItem = quantidadeItem,
            id = id))
    }

    fun findById(id: Int): Lista? {
        return listaDao[id]
    }

    fun findByPlanet(item: String): Lista? {
        return listaDao.values.find { it.item == item }
    }

    fun update(id: Int, lista: Lista){

        listaDao.put(id,
            Lista(
                item = lista.item,
                quantidadeItem = lista.quantidadeItem,
                id = id))
    }

    fun delete(id: Int) {
        listaDao.remove(id)
    }


}