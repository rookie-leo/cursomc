package com.leonardo.cursomc.services;

import com.leonardo.cursomc.config.exceptions.ObjectNotFoundException;
import com.leonardo.cursomc.model.ItemPedido;
import com.leonardo.cursomc.model.PagamentoComBoleto;
import com.leonardo.cursomc.model.Pedido;
import com.leonardo.cursomc.model.enuns.EstadoPagamento;
import com.leonardo.cursomc.repositories.ItemPedidoRepository;
import com.leonardo.cursomc.repositories.PagamentoRepository;
import com.leonardo.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PagamentoRepository pgtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    public Pedido find(Long id) {
        Optional<Pedido> pedido = repository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id:" + id));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pgto = (PagamentoComBoleto) pedido.getPagamento();
            PagamentoComBoleto.preencherPagamentoComBoleto(pgto, pedido.getInstante());
        }

        pedido = repository.save(pedido);
        pgtoRepository.save(pedido.getPagamento());

        for (ItemPedido item : pedido.getItens()) {
            item.setDesconto(0.0);
            item.setProduto(produtoService.find(item.getProduto().getId()));
            item.setPreco(item.getProduto().getPreco());
            item.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }

}
