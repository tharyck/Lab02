package com.produto.utils.exceptions;

public class UpdateConflictRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	private static final long serialVersionUID = 748599419111658401L;

	public UpdateConflictRuntimeException(String message) {
		super("Erro ao atualizar recurso. Detalhes: " + message);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.UPDATE_CONFLICT;
	}

}
